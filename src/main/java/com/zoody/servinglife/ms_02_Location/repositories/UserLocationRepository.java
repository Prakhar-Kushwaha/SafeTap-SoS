package com.zoody.servinglife.ms_02_Location.repositories;

import com.mongodb.client.model.geojson.Point;
import com.zoody.servinglife.ms_02_Location.DAO.User;
import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLocationRepository extends MongoRepository<UserLocation, String> {
    Optional<UserLocation> findByUsername(String username);

    // Find users within a radius using MongoDB geospatial queries
    @Query("{ 'currentLocation' : { $nearSphere : { $geometry : ?0 }, $maxDistance: ?1 } }")
    List<UserLocation> findNearbyUsers(GeoJsonPoint location, double maxDistanceMeters);

}

