package com.zoody.servinglife.ms_02_Location.sheduler;

import com.zoody.servinglife.ms_02_Location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationSheduler {

    @Autowired
    private  LocationService service;


//    @Scheduled(fixedRate = 1000*60*5) // Runs every 5 minutes
//    public void fetchUserLocations() {
//        // Call external service (if applicable) or assume mock locations
//        String userId = "some-user-id";
//        double lat = Math.random() * 90;
//        double lon = Math.random() * 180;
//
//        service.storeUserLocation(userId, lat, lon);
//    }
}
