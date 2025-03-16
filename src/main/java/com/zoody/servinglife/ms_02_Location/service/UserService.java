package com.zoody.servinglife.ms_02_Location.service;

import com.zoody.servinglife.ms_02_Location.DAO.User;
import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public User registerUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public boolean isLocationSharingEnabled(String userId){
        return userRepo.findByUsername(userId)
                .map(User::isLocationSharingEnabled)
                .orElse(false);
    }

}
