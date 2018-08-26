package com.zalerix.blogsource.springboot2demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Car {
    private String id = UUID.randomUUID().toString();

    @ApiModelProperty(value = "The name of the model", required = true)
    private String model;

    public Car(String model) {
        this.model = model;
    }
}
