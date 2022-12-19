package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.entity.EngineType;
import com.sample.carmarket.service.ManufactureService;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {

    @Autowired
    ManufactureService manufactureService;

    @Autowired
    private Table<Manufacturer> table;
    @Autowired
    private Notifications notifications;

    @Autowired
    private DataManager dataManager;

    private static final Long DEFAULT_VALUE = 0L;

    @Subscribe("table.calcCars")
    public void onTableCalcCars(Action.ActionPerformedEvent event) {
        Manufacturer selected = table.getSingleSelected();

        Map<String, Long> countCarsByEngine = manufactureService.getCountEngineTypeForModel(selected);

        notifications
                .create()
                .withCaption("Electric cars: "
                        + countCarsByEngine.getOrDefault("E", DEFAULT_VALUE)
                        + ", gasoline cars: " + countCarsByEngine.getOrDefault("G", DEFAULT_VALUE))
                .show();

    }
}