package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.ChargingPreferences;
import com.extrawest.ocpi.model.dto.SessionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CpoSessionsService {

    List<SessionDto> getSessions(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

    ChargingPreferences putChargingPreferences(String sessionId, ChargingPreferences chargingPreferencesDTO);
}
