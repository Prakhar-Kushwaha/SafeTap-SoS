package com.zoody.servinglife.ms_02_Location.service;


import com.zoody.servinglife.ms_02_Location.API_Client.ExternalLocationApiClient;
import com.zoody.servinglife.ms_02_Location.DAO.User;
import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import com.zoody.servinglife.ms_02_Location.repositories.UserLocationRepository;
import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data

public class LocationService {

    @Autowired
    private UserLocationRepository userLocationRepository;


    @Transactional
    public void storeUserLocation(String userId, double latitude, double longitude) {
       Optional<UserLocation> userLocation = userLocationRepository.findByUsername(userId);

       if(userLocation.isPresent()){
           userLocation.get().setCurrentLocation(new GeoJsonPoint(longitude , latitude));
           userLocation.get().addLocation(longitude , latitude);
           userLocationRepository.save(userLocation.get());
       }
    }

    @Autowired
    private UserRepository userRepo;


    public void fetchandStoreLocations() {
        List<User> list = userRepo.findByLocationSharingEnabledTrue();
        System.out.println("\n\n\n*******************\nUsers with Enabled Location service \n\n******************");
        list.forEach(
                user -> {
                    System.out.println(user.getUsername());
                    GeoJsonPoint currentLocation = ExternalLocationApiClient.getLocation(user.getUsername());
                    if (currentLocation != null) {

                        Optional<UserLocation> optionalUserLocation = userLocationRepository.findByUsername(user.getUsername());
                          UserLocation userLocation = optionalUserLocation.orElse(new UserLocation());
                          if(userLocation.getUsername() == null)
                          {
                             userLocation.setUsername(user.getUsername());
                          }
                            userLocation.setCurrentLocation(currentLocation);
                            userLocation.addLocation(currentLocation.getX(), currentLocation.getY());
                            userLocationRepository.save(userLocation);

                        System.out.println("saved location of User " + user.getUsername() + "\n");
                    }
                }
        );//For each
        System.out.println("\n\n*******************");
    }

}
