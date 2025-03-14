package com.zoody.servinglife.ms_02_Location.repositories;

import com.zoody.servinglife.ms_02_Location.DAO.UserLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocationRepository extends MongoRepository<UserLocation, String> {
    List<UserLocation> findByUserId(String userId);
}

