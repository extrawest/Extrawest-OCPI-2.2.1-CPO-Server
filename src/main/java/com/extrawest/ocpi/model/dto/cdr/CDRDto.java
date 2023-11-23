package com.extrawest.ocpi.model.dto.cdr;

import com.extrawest.ocpi.model.dto.ChargingPeriod;
import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.dto.Price;
import com.extrawest.ocpi.model.dto.tariff.TariffDto;
import com.extrawest.ocpi.model.enums.AuthMethod;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CDRDto extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    /**
     * Start timestamp of the charging session, or in-case of a reservation (before the start of a session) the start
     * of the reservation.
     */
    @NotNull
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    /**
     * The timestamp when the session was completed/finished, charging might have finished before the session ends,
     * for example: EV is full, but parking cost also has to be paid.
     */
    @NotNull
    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;

    /**
     * Unique ID of the Session for which this CDR is sent. Is only allowed to be omitted when the CPO has not
     * implemented the Sessions module or this CDR is the result of a reservation that never became a charging session,
     * thus no OCPI Session.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * Token used to start this charging session, including all the relevant information to identify the unique token.
     */
    @NotNull
    @JsonProperty("cdr_token")
    private CdrToken cdrToken;

    /**
     * Method used for authentication. Multiple AuthMethods are possible during a charging
     * sessions, for example when the session was started with a reservation: ReserveNow: COMMAND. When the driver
     * arrives and starts charging using a Token that is whitelisted: WHITELIST.
     * The last method SHALL be used in the CDR.
     */
    @NotNull
    @JsonProperty("auth_method")
    private AuthMethod authMethod;

    /**
     * Reference to the authorization given by the eMSP. When the eMSP provided an authorization_reference in either:
     * real-time authorization, StartSession or ReserveNow, this field SHALL contain the same value.
     * When different authorization_reference values have been given by the eMSP that are relevant to this Session,
     * the last given value SHALL be used here.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;

    /**
     * Location where the charging session took place, including only the relevant EVSE and Connector.
     */
    @NotNull
    @JsonProperty("cdr_location")
    private CdrLocation cdrLocation;

    /**
     * Identification of the Meter inside the Charge Point.
     */
    @JsonProperty("meter_id")
    private String meterId;

    /**
     * Currency of the CDR in ISO 4217 Code.
     */
    @NotBlank
    @Size(max = 3)
    private String currency;

    /**
     * List of relevant Tariff Elements, see: Tariff. When relevant, a Free of Charge tariff should also be in this
     * list, and point to a defined Free of Charge Tariff.
     */
    private List<TariffDto> tariffs;

    /**
     * List of Charging Periods that make up this charging session. A session consists of 1 or more periods,
     * where each period has a different relevant Tariff.
     */
    @NotEmpty
    @JsonProperty("charging_periods")
    private List<ChargingPeriod> chargingPeriods;

    /**
     * Signed data that belongs to this charging Session.
     */
    @JsonProperty("signed_data")
    private SignedData signedData;

    /**
     * Total sum of all the costs of this transaction in the specified currency.
     */
    @NotNull
    @JsonProperty("total_cost")
    private Price totalCost;

    /**
     * Total sum of all the fixed costs in the specified currency, except fixed price components of parking and
     * reservation. The cost not depending on amount of time/energy used etc. Can contain costs like a start tariff.
     */
    @JsonProperty("total_fixed_cost")
    private Price totalFixedCost;

    /**
     * Total energy charged, in kWh.
     */
    @NotNull
    @JsonProperty("total_energy")
    private Float totalEnergy;

    /**
     * Total sum of all the cost of all the energy used, in the specified currency.
     */
    @JsonProperty("total_energy_cost")
    private Price totalEnergyCost;

    /**
     * Total duration of the charging session (including the duration of charging and not charging), in hours.
     */
    @NotNull
    @JsonProperty("total_time")
    private Float totalTime;

    /**
     * Total sum of all the cost related to duration of charging during this transaction, in the specified currency.
     */
    @JsonProperty("total_time_cost")
    private Price totalTimeCost;

    /**
     * Total duration of the charging session where the EV was not charging (no energy was transferred between EVSE and EV), in
     * hours.
     */
    @JsonProperty("total_parking_time")
    private Float totalParkingTime;

    /**
     * Total sum of all the cost related to parking of this transaction, including fixed price components, in the
     * specified currency.
     */
    @JsonProperty("total_parking_cost")
    private Price totalParkingCost;

    /**
     * Total sum of all the cost related to a reservation of a Charge Point, including fixed price components,
     * in the specified currency.
     */
    @JsonProperty("total_reservation_cost")
    private Price totalReservationCost;

    /**
     * Optional remark, can be used to provide additional human-readable information to the CDR, for example: reason why
     * a transaction was stopped.
     */
    @Size(min = 1, max = 255)
    private String remark;

    /**
     * This field can be used to reference an invoice, that will later be sent for this CDR. Making it easier to link a
     * CDR to a given invoice. Maybe even group CDRs that will be on the same invoice.
     */
    @Size(min = 1, max = 39)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("invoice_reference_id")
    private String invoiceReferenceId;

    /**
     * When set to true, this is a Credit CDR, and the field credit_reference_id needs to be set as well.
     */
    private Boolean credit;

    /**
     * Is required to be set for a Credit CDR. This SHALL contain the id of the CDR for which this is a Credit CDR.
     */
    @Size(min = 1, max = 39)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("credit_reference_id")
    private String creditReferenceId;

    /**
     * When set to true, this CDR is for a charging session using the home charger of the EV Driver for which the energy
     * cost needs to be financial-compensated to the EV Driver
     */
    @JsonProperty("home_charging_compensation")
    private Boolean homeChargingCompensation;
}
