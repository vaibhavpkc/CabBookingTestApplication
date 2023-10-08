package com.vaibhav.cabbooking.database;

import com.vaibhav.cabbooking.exceptions.RiderAlreadyExistsException;
import com.vaibhav.cabbooking.exceptions.RiderNotFoundException;
import com.vaibhav.cabbooking.model.Rider;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {

    Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull final Rider newRider) {
        if (riders.containsKey(newRider.getName())) {
            throw new RiderAlreadyExistsException();
        }

        riders.put(newRider.getName(), newRider);
    }

    public Rider getRider(@NonNull final String riderName) {
        if (!riders.containsKey(riderName)) {
            throw new RiderNotFoundException();
        }
        return riders.get(riderName);
    }
}
