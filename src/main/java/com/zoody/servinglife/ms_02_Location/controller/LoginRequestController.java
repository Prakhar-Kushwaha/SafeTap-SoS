package com.zoody.servinglife.ms_02_Location.controller;


//Class to Create Login page to get , token on Login Request


import com.zoody.servinglife.ms_02_Location.DAO.User;
import com.zoody.servinglife.ms_02_Location.DTO.AuthResponse;
import com.zoody.servinglife.ms_02_Location.DTO.LoginRequest;
import com.zoody.servinglife.ms_02_Location.JJWT.JwtUtil;
import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginContext;
import java.io.DataInput;
import java.util.Optional;

@RestController
@RequestMapping("/SafeTap-SoS")
@Tag(name = "Login_Request_Controller" , description = "Not secured")
public class LoginRequestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepo;

    @Autowired private PasswordEncoder encoder;



    //Just doing this , so that we can get the jwt token for developmen purpose , by the way , it is a resource authenticated , so it will get jwt token from another service
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {


        User user = userRepo.findByUsername(request.getUsername()).orElse(null);

        if (user != null && encoder.matches(request.getPassword() , user.getPassword()) ) {


            //Updating fmcToken
            user.setFcmToken(request.getFcmToken());
            String token = jwtUtil.generateToken(user.getId());
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    @PostMapping("/{userId}/updateFcmToken")
    public ResponseEntity<String> updateFcmToken(@PathVariable String userId, @RequestParam String fcmToken) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            if (!existingUser.getFcmToken().contains(fcmToken)) {
                existingUser.getFcmToken().add(fcmToken); // Add only if not already stored
                userRepo.save(existingUser);
            }
            return ResponseEntity.ok("FCM Token Updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }


}
