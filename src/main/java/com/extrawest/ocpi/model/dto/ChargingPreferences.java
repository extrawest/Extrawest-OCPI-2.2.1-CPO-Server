package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.enums.ProfileType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contains the charging preferences of an EV driver.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargingPreferences {
    /**
     * Type of Smart Charging Profile selected by the driver. The ProfileType has to be supported at the Connector
     * {@link Connector} and for every supported ProfileType, a Tariff MUST be provided.
     * This gives the EV driver the option between different pricing options.
     */
    @NotNull
    @JsonProperty("profile_type")
    private ProfileType profileType;
    /**
     * Expected departure. The driver has given this Date/Time as expected departure moment. It is only an estimation
     * and not necessarily the Date/Time of the actual departure.
     */
    @JsonProperty("departure_time")
    private LocalDateTime departureTime;
    /**
     * Requested amount of energy in kWh. The EV driver wants to have this amount of energy charged.
     */
    @JsonProperty("energy_need")
    private Float energyNeed;
    /**
     * The driver allows their EV to be discharged when needed, as long as the other preferences are met: EV is charged
     * with the preferred energy (energy_need) until the preferred departure moment (departure_time).
     * Default if omitted: false
     */
    @JsonProperty("discharge_allowed")
    private Boolean dischargeAllowed = false;
}
