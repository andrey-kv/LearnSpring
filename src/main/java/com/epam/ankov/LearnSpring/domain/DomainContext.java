package com.epam.ankov.LearnSpring.domain;

import com.epam.ankov.LearnSpring.entities.Contact;
import com.epam.ankov.LearnSpring.exceptions.NotFoundException;
import com.epam.ankov.LearnSpring.repositories.ContactRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainContext {
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});
    }};
    private int counter = messages.size() + 1;
    private final ContactRepository contactRepository;

    public DomainContext(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Iterable<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Map<String, String> getContactById(String id) {
        return getMessage(id);
    }

    public Map<String, String> save(Map<String, String> message) {
        Map<String, String> msg = new HashMap<>();
        msg.put("id", String.valueOf(counter++));
        msg.put("text", message.get("text"));
        messages.add(msg);
        return message;
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream().filter(message -> message.get("id").equals(id))
                .findFirst().orElseThrow(NotFoundException::new);

    }

    public Map<String, String> updateRecord(String id, Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

}
