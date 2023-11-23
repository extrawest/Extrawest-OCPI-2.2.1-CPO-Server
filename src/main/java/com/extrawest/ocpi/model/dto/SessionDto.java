package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.dto.cdr.CdrToken;
import com.extrawest.ocpi.model.enums.AuthMethod;
import com.extrawest.ocpi.model.enums.SessionStatus;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    /**
     * The timestamp when the session became ACTIVE in the Charge Point.
     * When the session is still PENDING, this field SHALL be set to the time the Session was created at the
     * Charge Point. When a Session goes from PENDING to ACTIVE, this field SHALL be updated to the moment
     * the Session went to ACTIVE in the Charge Point.
     */
    @NotNull
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    /**
     * The timestamp when the session was completed/finished, charging might have finished before the session ends,
     * for example: EV is full, but parking cost also has to be paid.
     */
    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;

    /**
     * How many kWh were charged.
     */
    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    @JsonProperty("kwh")
    private Float kwh;

    /**
     * Token used to start this charging session, including all the relevant information to identify the unique token.
     */
    @NotNull
    @JsonProperty("cdr_token")
    private CdrToken cdrToken;

    /**
     * Method used for authentication. This might change during a session, for example when the session was started
     * with a reservation: ReserveNow: COMMAND. When the driver arrives and starts charging using a Token that
     * is whitelisted: WHITELIST.
     */
    @NotNull
    @JsonProperty("auth_method")
    private AuthMethod authMethod;

    /**
     * Reference to the authorization given by the eMSP. When the eMSP provided an authorization_reference in either:
     * real-time authorization, StartSession or ReserveNow this field SHALL contain the same value. When different
     * authorization_reference values have been given by the eMSP that are relevant to this Session, the last given
     * value SHALL be used here.
     */
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("authorization_reference")
    private String authorizationReference;

    /**
     * Location.id of the Location object of this CPO, on which the charging session is/was happening.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * EVSE.uid of the EVSE of this Location on which the charging session is/was happening. Allowed to be set to:
     * #NA when this session is created for a reservation, but no EVSE yet assigned to the driver.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("evse_uid")
    private String evseUid;

    /**
     * Connector.id of the Connector of this Location where the charging session is/was happening. Allowed to be set to:
     * #NA when this session is created for a reservation, but no connector yet assigned to the driver.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("connector_id")
    private String connectorId;

    /**
     * Optional identification of the kWh meter.
     */
    @Size(min = 1, max = 255)
    @JsonProperty("meter_id")
    private String meterId;

    /**
     * ISO 4217 code of the currency used for this session.
     */
    @NotBlank
    @Size(min = 1, max = 3)
    @JsonProperty("currency")
    private String currency;

    /**
     * An optional list of Charging Periods that can be used to calculate and verify the total cost.
     */
    @JsonProperty("charging_periods")
    private List<ChargingPeriod> chargingPeriods;

    /**
     * The total cost of the session in the specified currency. This is the price that the eMSP will have to pay to the
     * CPO. A total_cost of 0.00 means free of charge. When omitted, i.e. no price information is given in the Session
     * object, it does not imply the session is/was free of charge.
     */
    @JsonProperty("total_cost")
    private Price totalCost;

    /**
     * The status of the session.
     */
    @NotNull
    @JsonProperty("status")
    private SessionStatus status;
}
