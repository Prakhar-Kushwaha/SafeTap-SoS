package com.zoody.servinglife.ms_02_Location.controller;


import com.zoody.servinglife.ms_02_Location.DAO.User;
import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import com.zoody.servinglife.ms_02_Location.service.LocationService;
import com.zoody.servinglife.ms_02_Location.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SafeTap-SoS")
@Tag(name = "User Service", description = "To Register and updating User Preference for Sharing Location")
public class UserLocationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

   // @Tag(name = "Register" , description = "Not Secured endpoint")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userService.registerUser(user);

        return ResponseEntity.ok(user);
    }


    //Imp :- Please take care , that Authentication != Authorization
    //Spring Security context is performing Authentication
    // We need to manually handle authorization

    @Autowired
    private LocationService locationService;

    @PostMapping("update-Location")
    public ResponseEntity<String> updateLocation(@RequestParam String username,
                                                 @RequestParam double lat,
                                                 @RequestParam double lon) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String userNa = userDetails.getUsername();
            if (userNa.equals(username)
                    &&
                    userDetails.getAuthorities().stream().anyMatch(
                            grantedAuthority -> grantedAuthority.getAuthority().equals("ACTIVE"))) {

                locationService.storeUserLocation(username, lat, lon);
                return ResponseEntity.ok("Location updated");
            }else {
                return new ResponseEntity<>("Not Registered for location sharing",HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Acessing unauthorized user", HttpStatus.UNAUTHORIZED);
        }
    }


//Authentication .getDetails() contains metadata   (e.g., IP address), not user info.


    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{username}/enable-location")
    public String enableLocation(@PathVariable String username) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String userNa = userDetails.getUsername();
            if (userNa.equals(username) ){
                if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("INACIVE")))
                {
                    User user = userRepository.findByUsername(username).get();//Because it will definately had user because of authentication
                    user.setLocationSharingEnabled(true);
                    userRepository.save(user);
                }
                return "Location Service Enabled For " + userDetails.getUsername();
            }else{
                return "Accessing enpoint with hijacked token \" (Token is Differnt from username)\"";
            }
        }
        return "Unauthorized access";

    }


}
