package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Location and EVSEs for which the Token is requested to be authorized.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationReferencesDto implements OcpiResponseData, OcpiRequestData {

    /**
     * Unique identifier for the location
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * Unique identifiers for EVSEs within the CPO’s platform for the EVSE within the
     * given location.
     */
    @JsonProperty("evse_uids")
    private List<@Size(min = 1, max = 36) @Pattern(regexp = Constants.ASCII_REGEXP) String> evseUids;
}
