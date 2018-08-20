package com.zalerix.blogsource.springboot2demo.repository;

import com.zalerix.blogsource.springboot2demo.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ReactiveMongoRepository<Car, String> {
}
