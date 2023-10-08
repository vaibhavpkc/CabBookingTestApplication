package com.vaibhav.cabbooking.database;

import com.vaibhav.cabbooking.exceptions.CabAlreadyExistsException;
import com.vaibhav.cabbooking.exceptions.CabNotFoundException;
import com.vaibhav.cabbooking.model.Driver;
import com.vaibhav.cabbooking.model.Location;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverManager {
    Map<String, Driver> drivers = new HashMap<>();

    public void createDriver(@NonNull final Driver newDriver) {
        if (drivers.containsKey(newDriver.getName())) {
            throw new CabAlreadyExistsException();
        }

        drivers.put(newDriver.getName(), newDriver);
    }

    public Driver getDriver(@NonNull final String driverName) {
        if (!drivers.containsKey(driverName)) {
            throw new CabNotFoundException();
        }
        return drivers.get(driverName);
    }

    public void updateDriverLocation(@NonNull final String driverName, @NonNull final Location newLocation) {
        if (!drivers.containsKey(driverName)) {
            throw new CabNotFoundException();
        }
        drivers.get(driverName).setCurrentLocation(newLocation);
    }

    public void updateDriverAvailability(
            @NonNull final String driverName, @NonNull final Boolean newAvailability) {
        if (!drivers.containsKey(driverName)) {
            throw new CabNotFoundException();
        }
        drivers.get(driverName).setIsAvailable(newAvailability);
    }

    public List<Driver> getDrivers(@NonNull final Location fromPoint, @NonNull final Double distance) {
        List<Driver> result = new ArrayList<>();
        for (Driver cab : drivers.values()) {
            // TODO: Use epsilon comparison because of double
            if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
                result.add(cab);
            }
        }
        return result;
    }
}
