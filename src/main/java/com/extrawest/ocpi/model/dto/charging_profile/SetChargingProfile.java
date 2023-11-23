package com.extrawest.ocpi.model.dto.charging_profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SetChargingProfile object with information needed to set/update the Charging Profile for a session.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetChargingProfile {
    /**
     * Contains limits for the available power or current over time.
     */
    @NotNull
    @JsonProperty("charging_profile")
    private ChargingProfile chargingProfile;

    /**
     * URL that the ChargingProfileResult POST should be sent to. This URL might contain unique ID to be able to
     * distinguish between GET ActiveChargingProfile requests.
     */
    @NotBlank
    @JsonProperty("response_url ")
    private String responseUrl;
}
