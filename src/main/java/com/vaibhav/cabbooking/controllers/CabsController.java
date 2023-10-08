package com.vaibhav.cabbooking.controllers;

import com.vaibhav.cabbooking.database.DriverManager;
import com.vaibhav.cabbooking.database.TripManager;
import com.vaibhav.cabbooking.model.Driver;
import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.RATING;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabsController {
    private DriverManager driverManager;
    private TripManager tripsManager;

    public CabsController(DriverManager driverManager, TripManager tripsManager) {
        this.driverManager = driverManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value= "/register/cab", method = RequestMethod.POST)
    public ResponseEntity registerDriver(final String driverName, final RATING driverRating){
        driverManager.createDriver(new Driver(driverName,driverRating));
        return ResponseEntity.ok("Driver creation success.");
    }


    @RequestMapping(value="/update/driver/location", method = RequestMethod.POST)
    public ResponseEntity updateDriverLocation(final String driverName, final Double newLongitude, final Double newLatitude){
        driverManager.updateDriverLocation(driverName,new Location(newLongitude,newLatitude));
        return ResponseEntity.ok("Location Updated.");
    }


    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(final String driverName, final Boolean newAvailability) {
        driverManager.updateDriverAvailability(driverName, newAvailability);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/end/trip", method = RequestMethod.POST)
    public ResponseEntity endTrip(final String cabId) {
        tripsManager.endTrip(driverManager.getDriver(cabId));
        return ResponseEntity.ok("");
    }
}