package com.zoody.servinglife.ms_02_Location.DAO;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "user_locations")
@CompoundIndexes({
        @CompoundIndex(name = "user_time_idx" , def = "{'userId': 1, 'timestamp': -1}")
})
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserLocation {

        @Id
        private String id;
        @Indexed(unique = true)
        private String userId;


        private double latitude;
        private double longitude;

        @Field("timestamp")
        @Indexed(expireAfter = "259200") // TTL index (3 days = 259200 seconds)


        private Date timestamp = new Date();

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public double getLatitude() {
                return latitude;
        }

        public void setLatitude(double latitude) {
                this.latitude = latitude;
        }

        public double getLongitude() {
                return longitude;
        }

        public void setLongitude(double longitude) {
                this.longitude = longitude;
        }
}


