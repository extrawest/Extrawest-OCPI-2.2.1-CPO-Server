package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ClientInfoDto;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.HubClientInfoService;
import com.extrawest.ocpi.service.pagination.PaginationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hub/api/2.2.1/hubClientInfo")
@Tag(name = "HubClientInfo")
public class HubClientInfoController {

    protected final HubClientInfoService hubClientInfoService;
    protected final PaginationService paginationService;

    protected HubClientInfoController(@Autowired HubClientInfoService hubClientInfoService,
                                      @Autowired PaginationService paginationService) {
        this.hubClientInfoService = hubClientInfoService;
        this.paginationService = paginationService;
    }

    /**
     * Get the list of known ClientInfo objects, last updated between the {date_from} and {date_to} paginated)
     *
     * @param dateFrom Only return ClientInfo that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return ClientInfo that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET.
     * @return List of all (or matching) ClientInfo objects.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<ClientInfoDto>>> getClientInfoList(
            @RequestParam(value = "date_from", required = false) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {
        int adjustedLimit = paginationService.adjustLimitByMax(limit);

        List<ClientInfoDto> clientsInfo = hubClientInfoService.getClientInfoList(dateFrom, dateTo, offset, adjustedLimit);
        long totalCount = hubClientInfoService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<ClientInfoDto>> responseFormat = new ResponseFormat<List<ClientInfoDto>>()
                .build(OcpiStatusCode.SUCCESS, clientsInfo);
        HttpHeaders responseHeaders = paginationService.buildHeader(offset, adjustedLimit, request, totalCount);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }
}
