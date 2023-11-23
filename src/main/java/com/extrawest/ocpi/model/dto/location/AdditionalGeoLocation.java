package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This class defines an additional geolocation that is relevant for the Charge Point.
 * The geodetic system to be used is WGS 84.
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AdditionalGeoLocation implements OcpiRequestData {
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

    @JsonProperty("name")
    private DisplayText name;
}
