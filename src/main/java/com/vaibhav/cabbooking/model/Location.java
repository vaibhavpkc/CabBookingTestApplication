package com.vaibhav.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@ToString
@Getter
@AllArgsConstructor
public class Location {

    // x axis
    private Double longitude;
    //y axis
    private Double latitude;

    public double distance(Location location2){
        return sqrt(pow(this.longitude-location2.longitude,2)+ pow(this.latitude - location2.latitude, 2));
    }
}
