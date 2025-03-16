package com.zoody.servinglife.ms_02_Location.API_Client;

import com.mongodb.client.model.geojson.CoordinateReferenceSystem;
import com.mongodb.client.model.geojson.Point;
import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

public class ExternalLocationApiClient {
    public static GeoJsonPoint getLocation(
            @RequestBody String userId) {

        //Fecth AddUserLocationResponse
        //Pass this to Service Sheduller

        return new GeoJsonPoint(Math.random() , Math.random());





        //return new UserLocation("id" , userId , null ,new Date() ,null) ;
    }


}
