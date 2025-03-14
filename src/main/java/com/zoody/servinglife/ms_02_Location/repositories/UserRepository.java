package com.zoody.servinglife.ms_02_Location.repositories;

import com.zoody.servinglife.ms_02_Location.DAO.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
