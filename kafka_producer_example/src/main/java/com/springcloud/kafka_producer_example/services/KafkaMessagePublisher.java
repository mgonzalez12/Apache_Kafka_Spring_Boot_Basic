package com.springcloud.kafka_producer_example.services;

import com.springcloud.kafka_producer_example.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("javatech_demo_events", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + customer.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            customer.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }


    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("javatech_demo_3", message);
        future.whenComplete( (result, ex) -> {
          //  result.getRecordMetadata().partition();
            if (ex == null){
                System.out.println("Sent message= [ " + message + "] with offset=[ " + result.getRecordMetadata().offset() + " ]");
            }else{
                System.out.println("Unable to send message= [ " + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
