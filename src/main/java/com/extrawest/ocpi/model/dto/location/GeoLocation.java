package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class defines the geolocation of the Charge Point. The geodetic system to be used is WGS 84.
 */
@Data
@NoArgsConstructor
public class GeoLocation implements OcpiRequestData, OcpiResponseData {

    /**
     * Latitude of the point in decimal degree. Example: 50.770774. Decimal separator: "."
     * Regex: -?[0-9]{1,2}\.[0-9]{5,7}
     */
    @NotBlank
    @Size(max = 10)
    @JsonProperty("latitude")
    private String latitude;
    /**
     * Longitude of the point in decimal degree. Example: -126.104965. Decimal separator: "."
     * Regex: -?[0-9]{1,3}\.[0-9]{5,7}
     */
    @NotBlank
    @Size(max = 11)
    @JsonProperty("longitude")
    private String longitude;
}
