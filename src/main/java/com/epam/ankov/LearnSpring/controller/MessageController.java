package com.epam.ankov.LearnSpring.controller;

import com.epam.ankov.LearnSpring.domain.DomainContext;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final DomainContext domainContext;

    public MessageController() {
        domainContext = new DomainContext();
    }

    @GetMapping
    public List<Map<String, String>> list() {
        return domainContext.getAll();
    }

    // fetch('/message', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ text: 'sdvsdgsdfgbsgb'})}).then(console.log)
    @PostMapping
    public Map<String, String> createRecord(@RequestBody Map<String, String> message) {
        return domainContext.save(message);
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageById(@PathVariable String id) {
        return domainContext.getMessageById(id);
    }

    // fetch('/message/3', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ text: '888888'})}).then(console.log)
    @PutMapping("{id}")
    public Map<String, String> updateRecord(@PathVariable String id, @RequestBody Map<String, String> message) {
        return domainContext.updateRecord(id, message);
    }

    // fetch('/message/3', { method: 'DELETE' }).then(console.log)
    @DeleteMapping("{id}")
    public void deleteRecord(@PathVariable String id) {
        domainContext.delete(id);
    }
}
