package com.extrawest.ocpi.model.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * With CancelReservation the Sender can request the Cancel of an existing Reservation.
 * The CancelReservation needs to contain the reservation_id that was given by the Sender to the ReserveNow.
 * As there might be cost involved for a Reservation, canceling a reservation might still result
 * in a CDR being sent for the reservation.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CancelReservation extends AbstractCommand {

    /**
     * Reservation id, unique for this reservation. If the Charge Point already has a reservation that matches
     * this reservationId the Charge Point will replace the reservation.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("reservation_id")
    private String reservationId;
}
