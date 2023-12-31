package com.extrawest.ocpi.model.dto.charging_profile;

import com.extrawest.ocpi.model.enums.ChargingRateUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Charging profile class defines a list of charging periods.
 */
@Data
@NoArgsConstructor
public class ChargingProfile {

    /**
     * Starting point of an absolute profile. If absent the profile will be relative to start of charging.
     */
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    /**
     * Duration of the charging profile in seconds. If the duration is left empty, the last period will continue
     * indefinitely or until end of the transaction in case start_date_time is absent.
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * The unit of measure.
     */
    @NotNull
    @JsonProperty("charging_rate_unit")
    private ChargingRateUnit chargingRateUnit;

    /**
     * Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter
     * is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case
     * a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
     */
    @JsonProperty("min_charging_rate")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float minChargingRate;

    /**
     * List of ChargingProfilePeriod elements defining maximum power or current usage over time.
     */
    @JsonProperty("charging_profile_period")
    private List<ChargingProfilePeriod> chargingProfilePeriod;
}
