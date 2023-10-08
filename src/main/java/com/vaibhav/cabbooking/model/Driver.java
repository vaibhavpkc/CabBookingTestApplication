package com.vaibhav.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class Driver {
    private String name;
    private RATING rating;

    @Setter Trip currentTrip;
    @Setter Location currentLocation;
    @Setter Boolean isAvailable;

    public Driver(String name, RATING rating){
        this.name= name;
        this.rating=rating;
        this.isAvailable=true;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", currentTrip=" + currentTrip +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
