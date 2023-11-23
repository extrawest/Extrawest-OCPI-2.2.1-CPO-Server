package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ChargingPreferences;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.SessionDto;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoSessionsService;
import com.extrawest.ocpi.service.pagination.PaginationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cpo/api/2.2.1/sessions")
@Tag(name = "CPOSessions")
public class CpoSessionsController {

    protected final CpoSessionsService cpoSessionsService;
    protected final PaginationService paginationService;

    protected CpoSessionsController(@Autowired CpoSessionsService cpoSessionsService,
                                    @Autowired PaginationService paginationService) {
        this.cpoSessionsService = cpoSessionsService;
        this.paginationService = paginationService;
    }

    /**
     * Fetch Session objects of charging sessions last updated between the {date_from} and {date_to} (paginated).
     *
     * @param dateFrom Only return Sessions that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return Sessions that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET
     * @return List of Session objects that match the request parameters.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<SessionDto>>> getSessions(
            @RequestParam(value = "date_from") LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {
        int adjustedLimit = paginationService.adjustLimitByMax(limit);

        List<SessionDto> sessions = cpoSessionsService.getSessions(dateFrom, dateTo, offset, adjustedLimit);
        long totalCount = cpoSessionsService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<SessionDto>> responseFormat = new ResponseFormat<List<SessionDto>>()
                .build(OcpiStatusCode.SUCCESS, sessions);
        HttpHeaders responseHeaders = paginationService.buildHeader(offset, adjustedLimit, request, totalCount);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }

    /**
     * Setting Charging Preferences of an ongoing session.
     *
     * @param sessionId              Setting Charging Preferences of an ongoing session.
     * @param chargingPreferencesDTO Updated Charging Preferences of the driver for this Session.
     * @return Response to the Charging Preferences PUT request.
     */
    @PutMapping
    public ResponseEntity<ResponseFormat<ChargingPreferences>> putChargingPreferences(
            @RequestParam(value = "session_id") String sessionId,
            @RequestBody @Valid ChargingPreferences chargingPreferencesDTO) {

        ChargingPreferences chargingPreferences =
                cpoSessionsService.putChargingPreferences(sessionId, chargingPreferencesDTO);

        ResponseFormat<ChargingPreferences> responseFormat = new ResponseFormat<ChargingPreferences>()
                .build(OcpiStatusCode.SUCCESS, chargingPreferences);
        return ResponseEntity.ok(responseFormat);
    }

}
