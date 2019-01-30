package com.epam.ankov.LearnSpring.controller;

import com.epam.ankov.LearnSpring.domain.DomainContext;
import com.epam.ankov.LearnSpring.entities.Contact;
import com.epam.ankov.LearnSpring.exceptions.NotFoundException;
import com.epam.ankov.LearnSpring.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    private final DomainContext domainContext;

    public ContactController() {
        domainContext = new DomainContext(contactRepository);
    }

    @GetMapping
    public Iterable<Contact> list() {
        return contactRepository.findAll();
    }

    @GetMapping(value = {"list"})
    public Map<String, String> list1(@RequestParam Map<String, String> allRequestParams) {
        return allRequestParams;
    }

    // fetch('/contacts', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ name: 'sdvsdgsdfgbsgb'})}).then(console.log)
    @PostMapping
    public void create(@RequestBody Contact contact) {
        contact.setId(null);
        contactRepository.save(contact);
        // return domainContext.save(message);
    }

    @GetMapping("{id}")
    public Contact getContactById(@PathVariable String id) {
        return getContact(id);
    }

    // fetch('/contacts/3', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ text: '888888'})}).then(console.log)
    @PutMapping("{id}")
    public Contact update(@PathVariable String id, @RequestBody Contact modifiedContact) {
        Contact contact = getContact(id);
        setValue(contact::setName, modifiedContact.getName());
        setValue(contact::setEmail, modifiedContact.getEmail());
        contactRepository.save(contact);
        return contact;
    }

    private void setValue(Consumer<String> setter, String name) {
        if (name != null) {
            setter.accept(name);
        }
    }

    // fetch('/contacts/3', { method: 'DELETE' }).then(console.log)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Contact contact = getContact(id);
        contactRepository.delete(contact);
    }

    private Contact getContact(@PathVariable String id) {
        try {
            int idx = Integer.parseInt(id);
            return contactRepository.findById(idx).orElseThrow(NotFoundException::new);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

}
