package com.bravi.contact_list.services;

import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.models.Person;

import java.util.List;

public interface PersonService {

    void insert(Person person);

    void update(Person person, String personName);

    void update(String personName, Contact newContact);

    void update(Person person);

    Person find(String name);

    void delete(String name);

    List<Person> list();

}
