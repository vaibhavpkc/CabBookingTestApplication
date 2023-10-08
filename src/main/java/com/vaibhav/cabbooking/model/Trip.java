package com.vaibhav.cabbooking.model;

import lombok.NonNull;
import lombok.ToString;

import static com.vaibhav.cabbooking.model.TRIP_STATUS.FINISHED;
import static com.vaibhav.cabbooking.model.TRIP_STATUS.IN_PROGRESS;

enum TRIP_STATUS{
    IN_PROGRESS,
    FINISHED
}
@ToString
public class Trip {
    private Rider rider;
    private Driver driver;
    private Location fromPoint;
    private Location toPoint;
    private TRIP_STATUS tripStatus;
    private Double price;

    public Trip(@NonNull final Rider rider, @NonNull final Driver driver, @NonNull final Double price, @NonNull final Location fromPoint, @NonNull final Location toPoint) {
        this.rider = rider;
        this.driver = driver;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.tripStatus = IN_PROGRESS;
    }
    public void endTrip() {
        this.tripStatus = FINISHED;
    }
}
