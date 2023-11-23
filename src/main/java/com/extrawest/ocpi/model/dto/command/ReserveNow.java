package com.extrawest.ocpi.model.dto.command;

import com.extrawest.ocpi.model.dto.SessionDto;
import com.extrawest.ocpi.model.dto.cdr.CDRDto;
import com.extrawest.ocpi.model.dto.location.EVSE;
import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ReserveNow object, for the RESERVE_NOW command, with information needed to reserve
 * a (specific) connector of a Charge Point for a given {@link TokenDto}.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReserveNow extends AbstractCommand {

    /**
     * Token object for how to reserve this Charge Point (and specific EVSE).
     */
    @NotNull
    @JsonProperty("token")
    private TokenDto token;

    /**
     * The Date/Time when this reservation ends, in UTC
     */
    @NotNull
    @JsonProperty("expiry_date")
    private LocalDateTime expireDate;

    /**
     * Reservation id, unique for this reservation. If the Receiver (typically CPO) Point already has a reservation
     * that matches this reservationId for that Location it will replace the reservation.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("reservation_id")
    private String reservationId;

    /**
     * Location.id of the Location (belonging to the CPO this request is send to) for which to reserve an {@link EVSE}.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * Optional EVSE.uid of the EVSE of this Location if a specific EVSE has to be reserved.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("evse_uid")
    private String evseUid;

    /**
     * Reference to the authorization given by the eMSP, when given, this reference will be provided
     * in the relevant {@link SessionDto} and/or {@link CDRDto}.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;
}
