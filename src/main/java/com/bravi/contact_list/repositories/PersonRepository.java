package com.bravi.contact_list.repositories;

import com.bravi.contact_list.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String>, PersonRepositoryCustom {

    Person findByName(String name);

}
