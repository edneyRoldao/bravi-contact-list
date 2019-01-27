package com.bravi.contact_list.controllers;

import com.bravi.contact_list.models.Contact;
import com.bravi.contact_list.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravi.contact_list.utils.SwaggerConstants.*;

@Api
@RestController
@RequestMapping("contacts")
public class ContactController {

    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = GET_CONTACT_VALUE, notes = GET_CONTACT_NOTE)
    public Contact getOne(@RequestParam String personName, @RequestParam String contactType) {
        return service.find(personName, contactType);
    }

    @GetMapping("/all/{personName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = LIST_CONTACTS_VALUE, notes = LIST_CONTACTS_NOTE)
    public List<Contact> getAll(@PathVariable String personName) {
        return service.list(personName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = INSERT_CONTACT_VALUE, notes = INSERT_CONTACT_NOTE)
    public void add(@RequestParam String personName, @RequestBody Contact contact) {
        service.add(personName, contact);
    }

    @PutMapping("/update/{personName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = UPDATE_CONTACTS_VALUE, notes = UPDATE_CONTACTS_NOTE)
    public void update(@PathVariable String personName, @RequestBody Contact contact) {
        service.update(personName, contact);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = DELETE_CONTACT_VALUE, notes = DELETE_CONTACT_NOTE)
    public void delete(@RequestParam String personName, @RequestBody Contact contact) {
        service.delete(personName, contact);
    }

}
