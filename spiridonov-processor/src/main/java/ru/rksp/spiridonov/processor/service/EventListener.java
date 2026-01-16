package ru.rksp.spiridonov.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.rksp.spiridonov.processor.model.DeliveryEvent;
import ru.rksp.spiridonov.processor.model.RawEvent;
import ru.rksp.spiridonov.processor.repository.RawEventRepository;

@Service
public class EventListener {
    
    @Autowired
    private RawEventRepository rawEventRepository;
    
    @RabbitListener(queues = "events.raw")
    public void processEvent(DeliveryEvent event) {
        System.out.println("Received event from queue: " + event);
        
        RawEvent rawEvent = new RawEvent();
        rawEvent.setОтправитель(event.getОтправитель());
        rawEvent.setПолучатель(event.getПолучатель());
        rawEvent.setАдресДоставки(event.getАдресДоставки());
        rawEvent.setСтатус(event.getСтатус());
        rawEvent.setДатаСобытия(event.getДатаСобытия());
        
        rawEventRepository.save(rawEvent);
        System.out.println("Event saved to PostgreSQL");
    }
}
