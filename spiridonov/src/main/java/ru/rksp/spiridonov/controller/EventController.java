package ru.rksp.spiridonov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.spiridonov.model.DeliveryEvent;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @PostMapping
    public ResponseEntity<String> receiveEvent(@RequestBody DeliveryEvent event) {
        rabbitTemplate.convertAndSend("events.raw", event);
        return ResponseEntity.ok("Event sent to queue successfully");
    }
}
