package com.springcloud.kafka_producer_example.controllers;

import com.springcloud.kafka_producer_example.dto.Customer;
import com.springcloud.kafka_producer_example.services.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @PostMapping("/publish")
    public void sendEvent(@RequestBody Customer customer){
        publisher.sendEventsToTopic(customer);
    }

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            for (int i = 0; i <10; i++){
            publisher.sendMessageToTopic(message + ": " + i);

            }
            return ResponseEntity.ok("message successfully");
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
