package com.sample.carmarket.service;

import com.sample.carmarket.condition.ConditionCar;
import com.sample.carmarket.entity.Car;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sample.carmarket.entity.Status.SOLD;

@Service
public class CarService {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private ConditionCar conditionCar;

    public boolean changeStatus(Car car) {

        if (conditionCar.checkStatusIsSold(car)) {
            return false;
        } else {
            car.setStatus(SOLD);
            car.setDateOfSale(new Date(System.currentTimeMillis()));
            dataManager.save(car);

            return true;
        }
    }
}
