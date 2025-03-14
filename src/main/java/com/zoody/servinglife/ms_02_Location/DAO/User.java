package com.zoody.servinglife.ms_02_Location.DAO;


import com.zoody.servinglife.ms_02_Location.repositories.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Data
@Getter
@Setter
@Document(collection = "users")
public class User  {


    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String username;
    private String password;
    private boolean locationSharingEnabled;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocationSharingEnabled() {
        return locationSharingEnabled;
    }

    public void setLocationSharingEnabled(boolean locationSharingEnabled) {
        this.locationSharingEnabled = locationSharingEnabled;
    }


}

