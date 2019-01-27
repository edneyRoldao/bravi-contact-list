package com.bravi.contact_list.services.impl;

import com.bravi.contact_list.exceptions.ContactAlreadyUsedException;
import com.bravi.contact_list.exceptions.InvalidDataException;
import com.bravi.contact_list.exceptions.PersonAlreadyUsedException;
import com.bravi.contact_list.exceptions.PersonNotFoundException;
import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.repositories.PersonRepository;
import com.bravi.contact_list.services.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bravi.contact_list.utils.ErrorMessages.*;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public void insert(Person person) {
        if (person == null || StringUtils.isEmpty(person.getName()))
            throw new InvalidDataException(PERSON_INVALID_FORMAT);

        if (repository.findByName(person.getName()) != null)
            throw new PersonAlreadyUsedException(PERSON_NAME_USED);

        repository.insert(person);
    }

    @Override
    public void update(Person person, String personName) {
        if (person == null || StringUtils.isEmpty(person.getName()))
            throw new InvalidDataException(PERSON_INVALID_FORMAT);

        Person personToBeUpdated = find(personName);

        personToBeUpdated.setName(person.getName());

        if (person.getContacts() != null && !person.getContacts().isEmpty())
            personToBeUpdated.getContacts().addAll(person.getContacts());

        repository.update(personToBeUpdated);
    }

    @Override
    public void update(String personName, Contact newContact) {
        if (personName == null || StringUtils.isEmpty(personName))
            throw new InvalidDataException(PERSON_INVALID_FORMAT);

        Person personToBeUpdated = find(personName);

        personToBeUpdated.getContacts().forEach(c -> {
            if (c.getType().equalsIgnoreCase(newContact.getType()))
                throw new ContactAlreadyUsedException(CONTACT_TYPE_USED);
        });

        personToBeUpdated.getContacts().add(newContact);
        repository.update(personToBeUpdated);
    }

    @Override
    public void update(Person person) {
        repository.update(person);
    }

    @Override
    public Person find(String name) {
        if (StringUtils.isEmpty(name))
            throw new InvalidDataException(PERSON_INVALID_FORMAT);

        Person person = repository.findByName(name);

        if (person == null)
            throw new PersonNotFoundException(PERSON_NOT_FOUND.replace("?", name));

        return person;
    }

    @Override
    public void delete(String name) {
        Person person = find(name);
        repository.delete(person);
    }

    @Override
    public List<Person> list() {
        return repository.findAll();
    }

}
