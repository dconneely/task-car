package com.davidconneely.car.repository;

import com.davidconneely.car.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class CarRepository {
    private final Map<Long, Car> cars = Map.of(
            1L, new Car(1L, "Avensis"),
            2L, new Car(2L, "Aygo"),
            3L, new Car(3L, "C-HR"),
            4L, new Car(4L, "RAV4"),
            5L, new Car(5L, "Yaris"));

    public Iterable<Car> findAll() {
        return cars.values();
    }

    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }
}
