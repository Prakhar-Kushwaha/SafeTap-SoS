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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginContext;
import java.io.DataInput;

@RestController
@RequestMapping("/SafeTap-SoS")
@Tag(name = "Login_Request_Controller" , description = "Not secured")
public class LoginRequestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepo;



    //Just doing this , so that we can get the jwt token for developmen purpose , by the way , it is a resource authenticated , so it will get jwt token from another service
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {


        User user = userRepo.findByUsername(request.getUsername()).orElse(null);

        if (user != null && request.getPassword().equals(user.getPassword()) ) {

            String token = jwtUtil.generateToken(user.getId());
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}
