package com.bravi.contact_list.utils;

public class SwaggerConstants {

    // PEOPLE
    public static final String LIST_PEOPLE_NOTE = "service that will list all persons and its contacts";
    public static final String LIST_PEOPLE_VALUE = "list all people";
    public static final String GET_PEOPLE_NOTE = "service that will get a person based on name";
    public static final String GET_PEOPLE_VALUE = "get a person";
    public static final String INSERT_PEOPLE_NOTE = "service that will store a person on database";
    public static final String INSERT_PEOPLE_VALUE = "insert a person";
    public static final String UPDATE_PEOPLE_NOTE = "service that will update person's information on database. Note: to change contacts info you must use Contacts services provided here";
    public static final String UPDATE_PEOPLE_VALUE = "update a person";
    public static final String DELETE_PEOPLE_NOTE = "service that will delete a person on database";
    public static final String DELETE_PEOPLE_VALUE = "delete a person";

    // CONTACTS
    public static final String LIST_CONTACTS_NOTE = "service that will list all contacts based on person's owner";
    public static final String LIST_CONTACTS_VALUE = "list all contacts";
    public static final String GET_CONTACT_NOTE = "service that will get a contact based on person's owner";
    public static final String GET_CONTACT_VALUE = "get a contact";
    public static final String INSERT_CONTACT_NOTE = "service that will store a contact on database";
    public static final String INSERT_CONTACT_VALUE = "insert a contact";
    public static final String UPDATE_CONTACTS_NOTE = "service that will update the value of a contact based on person's owner. You won't be able to update contact type. You will need to remove it and insert the new one";
    public static final String UPDATE_CONTACTS_VALUE = "update the value of a contact";
    public static final String DELETE_CONTACT_NOTE = "service that will delete a contact based on person's owner";
    public static final String DELETE_CONTACT_VALUE = "delete a contact";

}
