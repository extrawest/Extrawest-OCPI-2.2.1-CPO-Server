package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.location.Location;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.model.markers.LocationData;
import com.extrawest.ocpi.service.CpoLocationService;
import com.extrawest.ocpi.service.pagination.PaginationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cpo/api/2.2.1/locations")
@Tag(name = "CPOLocation")
public class CpoLocationController {
    protected final CpoLocationService cpoLocationService;
    protected final PaginationService paginationService;

    protected CpoLocationController(@Autowired CpoLocationService cpoLocationService,
                                    @Autowired PaginationService paginationService) {
        this.cpoLocationService = cpoLocationService;
        this.paginationService = paginationService;
    }

    /**
     * Fetch a list of Locations, last updated between the {date_from} and {date_to} (paginated)
     *
     * @param dateFrom Only return Locations that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return Locations that have last_updated after or equal to this Date/Time (inclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET.
     * @return List of all Locations with valid EVSEs.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<Location>>> getLocations(
            @RequestParam(value = "date_from", required = false) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {

        int adjustedLimit = paginationService.adjustLimitByMax(limit);
        List<Location> locations = cpoLocationService.getLocations(dateFrom, dateTo, offset, adjustedLimit);

        long totalCount = cpoLocationService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<Location>> responseFormat = new ResponseFormat<List<Location>>()
                .build(OcpiStatusCode.SUCCESS, locations);
        HttpHeaders responseHeaders = paginationService.buildHeader(offset, adjustedLimit, request, totalCount);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }

    /**
     * Retrieve a Location as it is stored in the eMSP system.
     *
     * @param countryCode Country code of the CPO requesting data from the eMSP system.
     * @param partyId     Party ID (Provider ID) of the CPO requesting data from the eMSP system.
     * @param locationId  Location.id of the Location object to retrieve.
     * @return The Location object
     */
    @GetMapping("/{country_code}/{party_id}/{location_id}")
    public ResponseEntity<ResponseFormat<LocationData>> getLocation(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId) {
        LocationData locationData = cpoLocationService.getLocation(countryCode, partyId, locationId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Retrieve a Location as it is stored in the eMSP system.
     *
     * @param countryCode Country code of the CPO requesting data from the eMSP system.
     * @param partyId     Party ID (Provider ID) of the CPO requesting data from the eMSP system.
     * @param locationId  Location.id of the Location object to retrieve.
     * @param evseUid     EVSE.id of the EVSE object to retrieve.
     * @return The EVSE object
     */
    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}")
    public ResponseEntity<ResponseFormat<LocationData>> getEvse(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid) {
        LocationData locationData = cpoLocationService.getEvse(countryCode, partyId, locationId, evseUid);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Retrieve a Location as it is stored in the eMSP system.
     *
     * @param countryCode Country code of the CPO requesting data from the eMSP system.
     * @param partyId     Party ID (Provider ID) of the CPO requesting data from the eMSP system.
     * @param locationId  Location.id of the Location object to retrieve.
     * @param evseUid     EVSE.id of the EVSE object to retrieve.
     * @param connectorId Connector.id of the Connector object to retrieve.
     * @return The Connector object
     */
    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    public ResponseEntity<ResponseFormat<LocationData>> getConnector(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId) {
        LocationData locationData = cpoLocationService.getConnector(countryCode, partyId, locationId, evseUid, connectorId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }
}
