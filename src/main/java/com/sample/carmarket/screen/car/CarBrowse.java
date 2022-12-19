package com.sample.carmarket.screen.car;

import com.sample.carmarket.condition.ConditionCar;
import com.sample.carmarket.service.CarService;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.sample.carmarket.entity.Status.SOLD;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {

    @Autowired
    private Table<Car> carsTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CarService carService;

    @Subscribe("carsTable.markAsSold")
    public void onCarsTableMarkAsSold(Action.ActionPerformedEvent event) {
        Car selectedCar = carsTable.getSingleSelected();

        String message = carService.changeStatus(selectedCar) ? "Done" : "Already Sold";

        notifications
                .create()
                .withCaption(message)
                .show();
    }
}