package com.springcloud.kafka_producer_example.dto;

import lombok.Data;

@Data
public class Customer {

    private Integer id;
    private String name;
    private String email;
    private String contactNo;

}
