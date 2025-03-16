package com.zoody.servinglife.ms_02_Location.controller;


import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import com.zoody.servinglife.ms_02_Location.repositories.UserLocationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SafeTap-SoS")
@Tag(name = "Location Service", description = "Fetch user location by authentication")//"\nAPIs for managing user locations : This is authenticated endpoint \n but not authorized , means it can be used by valid token , irrespective of username \n(eg user01 can fetch user02 location )")
public class Location {
  @Autowired
    private UserLocationRepository userLocationRepository;





    @GetMapping("fetchLocation/{username}")
    public UserLocation getUserLocations(@PathVariable String username) {
        return userLocationRepository.findByUsername(username).orElseGet(null);//Locaion DAO is storing userName as userId
    }

}
