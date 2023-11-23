package com.extrawest.ocpi.model.dto.charging_profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The ActiveChargingProfile is the charging profile as calculated by the EVSE.
 */
@Data
@NoArgsConstructor
public class ActiveChargingProfile {

    /**
     * Date and time at which the Charge Point has calculated this ActiveChargingProfile.
     * All time measurements within the profile are relative to this timestamp.
     */
    @NotNull
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    /**
     * Charging profile structure defines a list of charging periods.
     */
    @NotNull
    @JsonProperty("charging_profile")
    private ChargingProfile chargingProfile;
}
