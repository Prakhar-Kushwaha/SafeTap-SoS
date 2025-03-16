package com.zoody.servinglife.ms_02_Location.DTO;

import lombok.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddLocationRequest {
        String username;
        String timeStamp;
        GeoJsonPoint currentLocation;

}
