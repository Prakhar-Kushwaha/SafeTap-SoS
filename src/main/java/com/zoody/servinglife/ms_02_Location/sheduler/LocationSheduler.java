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


    @Scheduled(fixedRate = 1000*60*15) // Runs every 15 minutes
    public void fetchAndStoreUserLocations() {
       service.fetchandStoreLocations();
    }
}
