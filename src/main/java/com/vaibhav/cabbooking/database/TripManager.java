package com.vaibhav.cabbooking.database;

import com.vaibhav.cabbooking.exceptions.NoCabsAvailableException;
import com.vaibhav.cabbooking.exceptions.TripNotFoundException;
import com.vaibhav.cabbooking.model.Driver;
import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.Rider;
import com.vaibhav.cabbooking.model.Trip;
import com.vaibhav.cabbooking.strategies.MatchingStrategy;
import com.vaibhav.cabbooking.strategies.PricingStrategy;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripManager {

    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;

    private DriverManager driverManager;
    private RiderManager riderManager;
    private MatchingStrategy matchingStrategy;
    private PricingStrategy pricingStrategy;

    private Map<String, List<Trip>> trips =new HashMap<>();


    public TripManager(DriverManager driverManager, RiderManager riderManager, PricingStrategy pricingStrategy, MatchingStrategy matchingStrategy){
        this.driverManager = driverManager;
        this.riderManager=riderManager;
        this.pricingStrategy=pricingStrategy;
        this.matchingStrategy=matchingStrategy;
    }

    public void createTrip(@NonNull final Rider rider, @NonNull final Location fromPoint, @NonNull final Location toPoint){
        final List<Driver> closeByDrivers =
                driverManager.getDrivers(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        final List<Driver> closeByAvailableDrivers = closeByDrivers.stream().filter(driver->driver.getCurrentTrip()==null).collect(Collectors.toList());

        final Driver selectedDriver =
                matchingStrategy.matchDriverToRider(rider, closeByDrivers,fromPoint,toPoint);
        if(selectedDriver==null){
            throw new NoCabsAvailableException();
        }

        final Double price = pricingStrategy.findPrice(fromPoint,toPoint);
        final Trip newTrip = new Trip(rider, selectedDriver, price, fromPoint, toPoint);
        if(!trips.containsKey(rider.getName())){
            trips.put(rider.getName(),new ArrayList<>());
        }
        trips.get(rider.getName()).add(newTrip);
        selectedDriver.setCurrentTrip(newTrip);
    }

    public List<Trip> tripHistory(@NonNull final Rider rider){
        return trips.get(rider.getName());
    }

    public void endTrip(@NonNull final Driver driver){
        if(driver.getCurrentTrip()==null){
            throw new TripNotFoundException();
        }
        driver.getCurrentTrip().endTrip();
    }
}
