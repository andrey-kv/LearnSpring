package com.epam.ankov.LearnSpring.repositories;

import com.epam.ankov.LearnSpring.entities.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Iterable<Contact> findByName(String name);
}
