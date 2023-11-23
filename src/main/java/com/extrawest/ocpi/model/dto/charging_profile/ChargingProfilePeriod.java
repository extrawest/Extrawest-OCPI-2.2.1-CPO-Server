package com.extrawest.ocpi.model.dto.charging_profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Charging profile period structure defines a time period in a charging profile, as used in: ChargingProfile
 */
@Data
@NoArgsConstructor
public class ChargingProfilePeriod {
    /**
     * Start of the period, in seconds from the start of profile.
     * The value of StartPeriod also defines the stop time of the previous period.
     */
    @NotNull
    @JsonProperty("start_period")
    private Integer startPeriod;

    /**
     * Charging rate limit during the profile period, in the applicable chargingRateUnit, for example in Amperes (A)
     * or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
     */
    @NotNull
    @JsonProperty("limit")
    private Float limit;
}
