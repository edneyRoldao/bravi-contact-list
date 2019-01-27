package com.bravi.contact_list.repositories.impl;

import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.repositories.PersonRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public PersonRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void update(Person person) {
        Query query = new Query(Criteria.where("_id").is(person.getId()));

        Update update = new Update()
                .set("name", person.getName())
                .set("contacts", person.getContacts());

        mongoTemplate.updateFirst(query, update, Person.class);
    }

}
