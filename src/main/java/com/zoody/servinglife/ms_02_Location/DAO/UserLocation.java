package com.zoody.servinglife.ms_02_Location.DAO;


import com.mongodb.client.model.geojson.Point;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.*;

@Document(collection = "user_locations")
@CompoundIndexes({
        @CompoundIndex(name = "user_time_idx" , def = "{'userId': 1, 'timestamp': '2dsphere'}")
})
@Data
@Getter
@Setter
@AllArgsConstructor

public class UserLocation {

        public UserLocation(){

        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public Date getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(Date timestamp) {
                this.timestamp = timestamp;
        }

        public List<GeoJsonPoint> getRecentLocations() {
                return recentLocations;
        }

        public void setRecentLocations(List<GeoJsonPoint> recentLocations) {
                this.recentLocations = recentLocations;
        }

        @Id
        private String id;
        @Indexed(unique = true)
        private String username;


        @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
        private GeoJsonPoint currentLocation;



        @Field("timestamp")
        private Date timestamp = new Date();
        @Field("recentLocations")
        private List<GeoJsonPoint> recentLocations = new ArrayList<>(10); // Stores last 10 locations

        public void addLocation(double latitude, double longitude) {
                if (recentLocations.size() >= 10) {
                        recentLocations.removeLast(); // Remove oldest entry
                }
                GeoJsonPoint newLocation = new GeoJsonPoint(longitude, latitude);
                recentLocations.add(newLocation);
                this.currentLocation = newLocation; // Update current location
                this.timestamp = new Date(); // Update timestamp
        }


        public GeoJsonPoint getCurrentLocation() {
                return currentLocation;
        }

        public void setCurrentLocation(GeoJsonPoint currentLocation) {
                this.currentLocation = currentLocation;
        }
}


