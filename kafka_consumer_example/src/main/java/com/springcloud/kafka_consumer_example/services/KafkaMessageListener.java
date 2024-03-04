package com.springcloud.kafka_consumer_example.services;

import com.springcloud.kafka_producer_example.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);


    @KafkaListener(topics = "javatech_demo_events", groupId = "stock-events-listener-group")
    public void consumerEvents(Customer customer){
        log.info("consumer consume the events {} ", customer.toString());
    }

    /*
    @KafkaListener(topics = "javatech_demo_3")
    public void consumer(String message){
        log.info("Consumer the message {} ", message);
    }
    */
}
