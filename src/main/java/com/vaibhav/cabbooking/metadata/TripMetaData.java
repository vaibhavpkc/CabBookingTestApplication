package com.vaibhav.cabbooking.metadata;

import com.vaibhav.cabbooking.model.Location;
import com.vaibhav.cabbooking.model.RATING;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TripMetaData {

    private RATING riderRating;
    private RATING driverRating;

    private Location srcLoc;
    private Location dstLoc;

    TripMetaData(Location srcLoc, Location dstLoc, RATING pRiderRating){
        this.srcLoc=srcLoc;
        this.dstLoc=dstLoc;
        this.riderRating=pRiderRating;
    }

}
