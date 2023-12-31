package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.charging_profile.ChargingProfileResponse;
import com.extrawest.ocpi.model.dto.charging_profile.SetChargingProfile;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoChargingProfilesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cpo/api/2.2.1/chargingProfiles")
@Tag(name = "CPOChargingProfiles")
public class CpoChargingProfilesController {

    protected final CpoChargingProfilesService cpoChargingProfilesService;

    protected CpoChargingProfilesController(@Autowired CpoChargingProfilesService cpoChargingProfilesService) {
        this.cpoChargingProfilesService = cpoChargingProfilesService;
    }

    /**
     * Gets the ActiveChargingProfile for a specific charging session.
     *
     * @param sessionId   The unique id that identifies the session in the CPO platform.
     * @param duration    Length of the requested ActiveChargingProfile in seconds Duration in seconds. *
     * @param responseUrl URL that the ActiveChargingProfileResult POST should be send to. This URL might contain
     *                    an unique ID to be able to distinguish between GET ActiveChargingProfile requests.
     * @return Result of the ActiveChargingProfile request, by the Receiver (Typically CPO), not the location/EVSE.
     * So this indicates if the Receiver understood the ChargingProfile request and was able to send it to the EVSE.
     * This is not the response by the Charge Point.
     */
    @GetMapping("/{session_id}/{duration}/{response_url}")
    public ResponseEntity<ResponseFormat<ChargingProfileResponse>> getChargingProfile(
            @PathVariable(value = "session_id") String sessionId,
            @PathVariable(value = "duration") Integer duration,
            @PathVariable(value = "response_url") String responseUrl
    ) {
        ChargingProfileResponse chargingProfileResponse =
                cpoChargingProfilesService.getChargingProfile(sessionId, duration, responseUrl);

        ResponseFormat<ChargingProfileResponse> responseFormat = new ResponseFormat<ChargingProfileResponse>()
                .build(OcpiStatusCode.SUCCESS, chargingProfileResponse);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Creates/updates a ChargingProfile for a specific charging session.
     *
     * @param sessionId                    The unique id that identifies the session in the CPO platform.
     * @param setChargingProfileRequestDTO SetChargingProfile object with information needed to set/update
     *                                     the Charging Profile for a session.
     * @return Result of the ChargingProfile PUT request, by the CPO (not the location/EVSE). So this indicates if
     * the CPO understood the ChargingProfile PUT request and was able to send it to the EVSE.
     * This is not the response by the Charge Point.
     */
    @PutMapping("/{session_id}")
    public ResponseEntity<ResponseFormat> putChargingProfile(
            @PathVariable(value = "session_id") String sessionId,
            @RequestBody @Valid SetChargingProfile setChargingProfileRequestDTO) {
        return ResponseEntity.ok(cpoChargingProfilesService.putChargingProfile(sessionId, setChargingProfileRequestDTO));
    }

    /**
     * Cancels an existing ChargingProfile for a specific charging session.
     *
     * @param sessionId   The unique id that identifies the session in the CPO platform.
     * @param responseUrl URL that the ClearProfileResult POST should be sent to. This URL might contain
     *                    unique ID to be able to distinguish between DELETE ChargingProfile requests.
     * @return Result of the ChargingProfile DELETE request, by the CPO (not the location/EVSE). So this indicates
     * if the CPO understood the ChargingProfile DELETE request and was able to send it to the EVSE. This is
     * not the response by the Charge Point.
     */
    @DeleteMapping("/{session_id}/{response_url}")
    public ResponseEntity<ResponseFormat<ChargingProfileResponse>> deleteChargingProfile(
            @PathVariable(value = "session_id") String sessionId,
            @PathVariable(value = "response_url") String responseUrl) {
        ChargingProfileResponse chargingProfileResponse =
                cpoChargingProfilesService.deleteChargingProfile(sessionId, responseUrl);

        ResponseFormat<ChargingProfileResponse> responseFormat = new ResponseFormat<ChargingProfileResponse>()
                .build(OcpiStatusCode.SUCCESS, chargingProfileResponse);
        return ResponseEntity.ok(responseFormat);
    }

}
