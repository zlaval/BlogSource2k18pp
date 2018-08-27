package com.zalerix.blogsource.springboot2demo.controller;

import com.zalerix.blogsource.springboot2demo.model.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Api(description = "Car controller")
@RestController
@RequestMapping(path = "v1")
public class CarController {

    @ApiOperation(value = "List of all cars", notes = "Return with a Flux")
    @GetMapping
    public Flux<Car> getCars() {
        Car corolla = new Car("Corolla");
        Car focus = new Car("Focus");
        return Flux.just(corolla, focus);
    }

    @GetMapping(path = "newcar/{model}")
    public Mono<Car> saveCar(@PathVariable String model) {
        Car car = new Car(model);
        return Mono.just(car);
    }

    @GetMapping(path = "flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Car> streamCars() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(x -> new Car(UUID.randomUUID().toString()));
    }

}
