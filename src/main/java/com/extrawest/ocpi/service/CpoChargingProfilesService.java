package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.charging_profile.ChargingProfile;
import com.extrawest.ocpi.model.dto.charging_profile.SetChargingProfile;

public interface CpoChargingProfilesService {

    ChargingProfile getChargingProfile(String sessionId, Integer duration, String responseUrl);

    ChargingProfile putChargingProfile(String sessionId, SetChargingProfile setChargingProfileRequestDTO);

    ChargingProfile deleteChargingProfile(String sessionId, String responseUrl);

}
