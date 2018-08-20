package com.zalerix.blogsource.springboot2demo.repository;

import com.zalerix.blogsource.springboot2demo.model.Car;
import com.zalerix.blogsource.springboot2demo.model.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CarRepositoryTest {


    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCarThenOk() {

        //given
        Producer producer = new Producer();
        producer.setName("Toyota");
        Car car = new Car();
        car.setModel("Corolla");
        car.setProducer(producer);

        //when
        carRepository.save(car).block();
        Car savedCar = carRepository.findById(car.getId()).block();
        //then
        assertNotNull(savedCar);

    }


}
