package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.dto.location.EVSE;
import com.extrawest.ocpi.model.dto.location.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface CpoLocationService {

    List<Location> getLocations(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

    Location getLocation(String locationId);

    EVSE getEvse(String locationId, String evseUid);

    Connector getConnector(String locationId, String evseUid, String connectorId);
}
