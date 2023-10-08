package com.vaibhav.cabbooking.controllers;

import com.vaibhav.cabbooking.database.RiderManager;
import com.vaibhav.cabbooking.database.TripManager;
import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.RATING;
import com.vaibhav.cabbooking.model.Rider;
import com.vaibhav.cabbooking.model.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiderController {

    private TripManager tripManager;
    private RiderManager riderManager;

    public RiderController(TripManager tripManager, RiderManager riderManager) {
        this.tripManager = tripManager;
        this.riderManager = riderManager;
    }

    @RequestMapping(value="/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderName, final RATING riderRating){
        riderManager.createRider(new Rider(riderName,riderRating));
        return ResponseEntity.ok("Rider is successfully registered.");
    }

    @RequestMapping(value="/book", method = RequestMethod.POST)
    public ResponseEntity book(final String riderName,
                               final Double longitudeSRC,
                               final Double latitudeSRC,
                               final Double longitudeDST,
                               final Double latitudeDST ){
        //calling create trip to create the trip using the inputs provided.
        tripManager.createTrip(riderManager.getRider(riderName),new Location(longitudeSRC,latitudeSRC), new Location(longitudeDST,latitudeDST));
        return ResponseEntity.ok("booking created for user: " + riderName+ ".");
    }

    @RequestMapping(value="/book/tripHistory", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderName){
        List<Trip> trips = tripManager.tripHistory(riderManager.getRider(riderName));
        return ResponseEntity.ok(trips);
    }
}
