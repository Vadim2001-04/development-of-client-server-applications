package ru.rksp.spiridonov.processor.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rksp.spiridonov.processor.model.DeliveryEvent;
import ru.rksp.spiridonov.processor.model.RawEvent;
import ru.rksp.spiridonov.processor.repository.RawEventRepository;

@Service
public class EventListener {
    
    @Autowired
    private RawEventRepository rawEventRepository;
    
    @RabbitListener(queues = "events.raw", containerFactory = "rabbitListenerContainerFactory")
    public void processEvent(DeliveryEvent event) {
        System.out.println("=== Received event from queue: " + event);
        
        try {
            RawEvent rawEvent = new RawEvent();
            rawEvent.setОтправитель(event.getОтправитель());
            rawEvent.setПолучатель(event.getПолучатель());
            rawEvent.setАдресДоставки(event.getАдресДоставки());
            rawEvent.setСтатус(event.getСтатус());
            rawEvent.setДатаСобытия(event.getДатаСобытия());
            
            rawEventRepository.save(rawEvent);
            System.out.println("=== Event saved to PostgreSQL with ID: " + rawEvent.getИдентификатор());
        } catch (Exception e) {
            System.err.println("=== Error processing event: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
