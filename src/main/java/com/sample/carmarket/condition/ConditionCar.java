package com.sample.carmarket.condition;

import com.sample.carmarket.entity.Car;
import org.springframework.stereotype.Component;

import static com.sample.carmarket.entity.Status.SOLD;

@Component
public class ConditionCar {

    public boolean checkStatusIsSold(Car car) {
        return car.getStatus() == SOLD ? true : false;
    }
}
