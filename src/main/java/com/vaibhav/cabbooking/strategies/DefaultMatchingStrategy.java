package com.vaibhav.cabbooking.strategies;

import com.vaibhav.cabbooking.model.Driver;
import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.Rider;
import lombok.NonNull;

import java.util.List;

public class DefaultMatchingStrategy implements MatchingStrategy {
    @Override
    public Driver matchDriverToRider(
            @NonNull final Rider rider,
            @NonNull final List<Driver> candidateCabs,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) {
        if (candidateCabs.isEmpty()) {
            return null;
        }
        return candidateCabs.get(0);
    }
}
