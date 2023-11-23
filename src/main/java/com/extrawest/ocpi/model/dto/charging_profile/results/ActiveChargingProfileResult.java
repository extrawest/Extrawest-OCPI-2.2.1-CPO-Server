package com.extrawest.ocpi.model.dto.charging_profile.results;

import com.extrawest.ocpi.model.dto.charging_profile.ActiveChargingProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The ActiveChargingProfileResult object is send by the CPO to the given response_url in a POST request.
 * It contains the result of the GET (ActiveChargingProfile) request send by the eMSP.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ActiveChargingProfileResult extends AbstractProfileResult {

    /**
     * The requested ActiveChargingProfile, if the result field is set to: ACCEPTED
     */
    @JsonProperty("profile")
    private ActiveChargingProfile profile;
}
