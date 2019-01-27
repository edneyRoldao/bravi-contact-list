package com.bravi.contact_list.services;

import com.bravi.contact_list.models.Contact;

import java.util.List;

public interface ContactService {

    void add(String personName, Contact contact);

    void delete(String personName, Contact contact);

    void update(String personName, Contact contact);

    Contact find(String personName, String type);

    List<Contact> list(String personName);

}
