package com.vaibhav.cabbooking.strategies;

import com.vaibhav.cabbooking.model.Driver;
import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.Rider;

import java.util.List;

public interface MatchingStrategy {
    Driver matchDriverToRider(Rider rider, List<Driver> candidateCabs, Location fromPoint, Location toPoint);
}
