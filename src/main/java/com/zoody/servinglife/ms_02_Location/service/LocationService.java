package com.zoody.servinglife.ms_02_Location.service;


import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import com.zoody.servinglife.ms_02_Location.repositories.UserLocationRepository;
import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class LocationService {

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void storeUserLocation(String userId , double latitude , double longitude)
    {
        if (userService.isLocationSharingEnabled(userId)) {
            UserLocation location = new UserLocation();
            location.setUserId(userId);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            userLocationRepository.save(location);
        }
    }
}
