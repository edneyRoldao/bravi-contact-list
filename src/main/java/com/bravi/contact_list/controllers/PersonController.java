package com.bravi.contact_list.controllers;

import com.bravi.contact_list.models.Person;
import com.bravi.contact_list.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravi.contact_list.utils.SwaggerConstants.*;

@Api
@RestController
@RequestMapping("people")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = GET_PEOPLE_VALUE, notes = GET_PEOPLE_NOTE)
    public Person getOne(@RequestParam String name) {
        return service.find(name);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = LIST_PEOPLE_VALUE, notes = LIST_PEOPLE_NOTE)
    public List<Person> getAll() {
        return service.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = INSERT_PEOPLE_VALUE, notes = INSERT_PEOPLE_NOTE)
    public void create(@RequestBody Person person) {
        service.insert(person);
    }

    @PutMapping("/update/{personName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = UPDATE_PEOPLE_VALUE, notes = UPDATE_PEOPLE_NOTE)
    public void update(@PathVariable String personName, @RequestBody Person person) {
        service.update(person, personName);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = DELETE_PEOPLE_VALUE, notes = DELETE_PEOPLE_NOTE)
    public void delete(@RequestParam String name) {
        service.delete(name);
    }

}
