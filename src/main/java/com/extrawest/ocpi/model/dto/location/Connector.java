package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.dto.AbstractDomainObject;
import com.extrawest.ocpi.model.enums.ConnectorFormat;
import com.extrawest.ocpi.model.enums.ConnectorType;
import com.extrawest.ocpi.model.enums.PowerType;
import com.extrawest.ocpi.model.markers.LocationData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A Connector is the socket or cable and plug available for the EV to use. A single EVSE may provide
 * multiple Connectors but only one of them can be in use at the same time.
 * A Connector always belongs to an EVSE object.
 */
@Data
@NoArgsConstructor
public class Connector extends AbstractDomainObject implements LocationData {
    /**
     * Identifier of the Connector within the EVSE. Two Connectors may have
     * the same id as long as they do not belong to the same EVSE object.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("id")
    private String connectorId;
    /**
     * The standard of the installed connector.
     */
    @NotNull
    @JsonProperty("standard")
    private ConnectorType standard;
    /**
     * The format (socket/cable) of the installed connector.
     */
    @NotNull
    @JsonProperty("format")
    private ConnectorFormat format;
    @NotNull
    @JsonProperty("power_type")
    private PowerType powerType;
    /**
     * Maximum voltage of the connector (line to neutral for AC_3_PHASE), in volt [V].
     * For example: DC Chargers might vary the voltage during charging when battery almost full.
     */
    @NotNull
    @JsonProperty("max_voltage")
    private Integer maxVoltage;
    /**
     * Maximum amperage of the connector, in ampere [A].
     */
    @NotNull
    @JsonProperty("max_amperage")
    private Integer maxAmperage;
    /**
     * Maximum electric power that can be delivered by this connector, in Watts (W). When the maximum electric power
     * is lower than the calculated value from voltage and amperage, this value should be set.
     * For example: A DC Charge Point which can delivers up to 920V and up to 400A can be limited to a maximum
     * of 150kW (max_electric_power = 150000). Depending on the car, it may supply max voltage or current,
     * but not both at the same time. For AC Charge Points, the amount of phases used can also have influence on the
     * maximum power
     */
    @JsonProperty("max_electric_power")
    private Integer maxElectricPower;
    /**
     * Identifiers of the currently valid charging tariffs. Multiple tariffs are possible, but only one of
     * each Tariff.type can be active at the same time. Tariffs with the same type are only allowed if they
     * are not active at the same time: start_date_time and end_date_time period not overlapping.
     * When preference-based smart charging is supported, one tariff for every possible ProfileType should be provided.
     * These tell the user about the options they have at this Connector, and what the tariff is for every option.
     * For a "free of charge" tariff, this field should be set and point to a defined "free of charge" tariff.
     */
    @JsonProperty("tariff_ids")
    private List<String> tariffIds;
    /**
     * URL to the operator’s terms and conditions.
     */
    @JsonProperty("terms_and_conditions")
    private String termsAndConditions;
    /**
     * Timestamp when this Connector was last updated (or created).
     */
    @NotNull
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
