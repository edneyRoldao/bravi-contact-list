package com.bravi.contact_list;

import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.repositories.PersonRepository;
import com.bravi.contact_list.services.PersonService;
import com.bravi.contact_list.services.impl.PersonServiceImpl;
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
public class PersonServiceIntegrationTest {

    @TestConfiguration
    static class PersonServiceIntegrationTestContextConfig {
        @Bean
        public PersonService personService() {
            return new PersonServiceImpl();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setup() {
        List<Contact> contacts =
                new ArrayList<>(Arrays.asList(new Contact("email", "email@email"),new Contact("phone", "444666")));

        Person person = new Person(new ObjectId(new Date()), "Edney", contacts);

        Mockito.when(personRepository.findByName(person.getName()))
                .thenReturn(person);
    }

    @Test
    public void whenNameIsValid_thenPersonShouldBeFound() {
        String name = "Edney";
        Person person = personService.find(name);
        assertThat(person.getName()).isEqualTo(name);
    }


}
