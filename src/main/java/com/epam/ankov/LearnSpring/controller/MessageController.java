package com.epam.ankov.LearnSpring.controller;

import com.epam.ankov.LearnSpring.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {

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

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOneRecord(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        Map<String, String> id1 = messages.stream().filter(message -> message.get("id").equals(id))
                .findFirst().orElseThrow(NotFoundException::new);
        return id1;
    }

    // fetch('/message', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ text: 'sdvsdgsdfgbsgb'})}).then(console.log)
    @PostMapping
    public Map<String, String> createRecord(@RequestBody Map<String, String> message) {
        Map<String, String> msg = new HashMap<String, String>();
        msg.put("id", String.valueOf(counter++));
        msg.put("text", message.get("text"));
        messages.add(msg);
        return message;
    }

    // fetch('/message/3', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ text: '888888'})}).then(console.log)
    @PutMapping("{id}")
    public Map<String, String> updateRecord(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    // fetch('/message/3', { method: 'DELETE' }).then(console.log)
    @DeleteMapping("{id}")
    public void deleteRecord(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
