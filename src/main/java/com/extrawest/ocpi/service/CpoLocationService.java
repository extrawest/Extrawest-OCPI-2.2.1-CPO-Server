package com.extrawest.ocpi.service;


import com.extrawest.ocpi.model.dto.AbstractDomainObject;
import com.extrawest.ocpi.model.dto.location.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface CpoLocationService {

    List<Location> getLocations(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    AbstractDomainObject getLocationEvseController(String locationId, String evseUid, String connectorId);

}
