package com.bravi.contact_list.services.impl;

import com.bravi.contact_list.exceptions.ContactNotFoundException;
import com.bravi.contact_list.exceptions.InvalidDataException;
import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.services.ContactService;
import com.bravi.contact_list.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bravi.contact_list.utils.ErrorMessages.*;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private PersonService personService;

    @Override
    public void add(String personName, Contact contact) {
        if (contact == null || isEmpty(contact.getType()) || isEmpty(contact.getValue()))
            throw new InvalidDataException(CONTACT_INVALID_FORMAT);

        personService.update(personName, contact);
    }

    @Override
    public void delete(String personName, Contact contact) {
        if (contact == null || isEmpty(contact.getType()) || isEmpty(contact.getValue()))
            throw new InvalidDataException(CONTACT_INVALID_FORMAT);

        Person person = personService.find(personName);

        if (person.getContacts() == null || person.getContacts().isEmpty()) {
            String msg = CONTACT_NOT_FOUND.replace("?1", personName).replace("?2", contact.getType());
            throw new ContactNotFoundException(msg);
        }

        if (!person.getContacts().remove(contact)) {
            String msg = CONTACT_NOT_FOUND.replace("?1", personName).replace("?2", contact.getType());
            throw new ContactNotFoundException(msg);
        }

        personService.update(person);
    }

    @Override
    public void update(String personName, Contact contact) {
        if (contact == null || isEmpty(contact.getType()) || isEmpty(contact.getValue()))
            throw new InvalidDataException(CONTACT_INVALID_FORMAT);

        Person person = personService.find(personName);

        Contact contactToBeUpdated = person.getContacts()
                                            .stream()
                                            .filter(c -> c.getType().equals(contact.getType()))
                                            .findFirst().orElse(null);

        if (contactToBeUpdated == null) {
            String msg = CONTACT_NOT_FOUND.replace("?1", personName).replace("?2", contact.getType());
            throw new ContactNotFoundException(msg);
        }

        contactToBeUpdated.setValue( contact.getValue() );
        personService.update(person);
    }

    @Override
    public Contact find(String personName, String type) {
        if (isEmpty(type))
            throw new InvalidDataException(CONTACT_TYPE_EMPTY);

        Person person = personService.find(personName);

        Contact contact = person.getContacts()
                                .stream()
                                .filter(c -> c.getType().equals(type))
                                .findFirst().orElse(null);

        if (contact == null) {
            String msg = CONTACT_NOT_FOUND.replace("?1", personName).replace("?2", type);
            throw new ContactNotFoundException(msg);
        }

        return contact;
    }

    @Override
    public List<Contact> list(String personName) {
        Person person = personService.find(personName);

        if (person.getContacts() == null || person.getContacts().isEmpty())
            throw new ContactNotFoundException(CONTACTS_LIST_EMPTY.replace("?", personName));

        return person.getContacts();
    }

}
