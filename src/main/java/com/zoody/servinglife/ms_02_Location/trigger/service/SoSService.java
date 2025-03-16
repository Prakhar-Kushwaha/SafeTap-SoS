package com.zoody.servinglife.ms_02_Location.trigger.service;


import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import com.zoody.servinglife.ms_02_Location.repositories.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Update it later
 */
@Service
public class SoSService {
    @Autowired private UserLocationRepository userLocationRepository;

    public List<UserLocation> triggerSoS(String userId , double latitude , double longitude){
        double searchRaduius = 5000; //5 km
        return userLocationRepository.findNearbyUsers(new GeoJsonPoint(longitude , latitude), searchRaduius);
    }

}
