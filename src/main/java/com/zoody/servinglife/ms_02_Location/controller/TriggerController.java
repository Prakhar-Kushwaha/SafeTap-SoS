package com.zoody.servinglife.ms_02_Location.controller;


import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import com.zoody.servinglife.ms_02_Location.trigger.service.SoSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SafeTap-SoS/emergency")
public class TriggerController {


    @Autowired
    SoSService soSService;


    //we will be using userid for logging purpose
    @GetMapping("/nearby-Users/{userId}")
    public List<UserLocation> getNearbyUsers(@PathVariable String userId ,
                                             @RequestParam double latitude,@RequestParam double longitude,@RequestParam double radius) {
        return soSService.triggerSoS(userId,longitude,latitude);
    }
}
