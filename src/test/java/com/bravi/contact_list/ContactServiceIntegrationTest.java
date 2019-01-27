package com.bravi.contact_list;

import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.services.ContactService;
import com.bravi.contact_list.services.PersonService;
import com.bravi.contact_list.services.impl.ContactServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ContactServiceIntegrationTest {

    @TestConfiguration
    static class PersonServiceIntegrationTestContextConfig {
        @Bean
        public ContactService personService() {
            return new ContactServiceImpl();
        }
    }

    @Before
    public void setup() {
        Contact[] list = new Contact[]{new Contact("phone", "(11) 988887777"), new Contact("business", "444666")};
        List<Contact> contacts = new ArrayList<>(Arrays.asList(list));

        Person person = new Person(new ObjectId(new Date()), "marjorie", contacts);

        Mockito.when(personService.find(person.getName())).thenReturn(person);
    }

    @Autowired
    private ContactService contactService;

    @MockBean
    private PersonService personService;

    @Test
    public void whenNameAndTyoeAreValid_thenContactShouldBeFound() {
        String name = "marjorie";
        String type = "phone";
        Contact contact = contactService.find(name, type);

        assertThat(contact.getType()).isEqualTo(type);
        assertThat(contact.getValue()).isEqualTo("(11) 988887777");
    }

}
