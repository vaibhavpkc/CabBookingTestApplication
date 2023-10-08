package com.vaibhav.cabbooking.strategies;

import com.vaibhav.cabbooking.model.Location;

public interface PricingStrategy {
        Double findPrice(Location fromPoint, Location toPoint);
}
