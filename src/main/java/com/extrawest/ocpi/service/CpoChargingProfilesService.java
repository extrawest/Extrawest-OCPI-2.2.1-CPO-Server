package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.charging_profile.ChargingProfileResponse;
import com.extrawest.ocpi.model.dto.charging_profile.SetChargingProfile;

public interface CpoChargingProfilesService {

    ChargingProfileResponse getChargingProfile(String sessionId, Integer duration, String responseUrl);

    ResponseFormat putChargingProfile(String sessionId, SetChargingProfile setChargingProfileRequestDTO);

    ChargingProfileResponse deleteChargingProfile(String sessionId, String responseUrl);
}
