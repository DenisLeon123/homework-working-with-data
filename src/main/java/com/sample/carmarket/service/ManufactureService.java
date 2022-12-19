package com.sample.carmarket.service;

import com.sample.carmarket.entity.Manufacturer;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManufactureService {
    @Autowired
    private DataManager dataManager;

    private static final String ENGINE = "engine";
    private static final String COUNT_CAR = "countCar";

    public Map<String, Long> getCountEngineTypeForModel(Manufacturer manufacturer) {
        return dataManager.loadValues("select model.engineType, count(c.id)" +
                        " from Model model" +
                        " left outer join Car c on model = c.model" +
                        " where model.manufacturer = :manufacturer" +
                        " group by model.engineType")
                .properties(ENGINE, COUNT_CAR)
                .parameter("manufacturer", manufacturer)
                .list().stream()
                .collect(Collectors.toMap(k -> k.getValue(ENGINE), v -> v.getValue(COUNT_CAR)));
    }
}
