package com.extrawest.ocpi.model.dto.charging_profile;

import com.extrawest.ocpi.model.enums.ChargingProfileResponseType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result of the ActiveChargingProfile request, by the Receiver (Typically CPO), not the
 * location/EVSE. So this indicates if the Receiver understood the ChargingProfile request
 * and was able to send it to the EVSE. This is not the response by the Charge Point.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargingProfileResponse {
    /**
     * Response from the CPO on the ChargingProfile request.
     */
    @NotNull
    private ChargingProfileResponseType result;

    /**
     * Timeout for this ChargingProfile request in seconds. When the Result is not received within this timeout,
     * the eMSP can assume that the message might never be sent.
     */
    @NotNull
    private Integer timeout;
}
