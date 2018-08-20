package com.zalerix.blogsource.springboot2demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Car {
    @Id
    private String id = UUID.randomUUID().toString();
    @DBRef
    private Producer producer;
    private String model;
}
