package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.cdr.CDRDto;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoCdrService;
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
@RequestMapping("/cpo/api/2.2.1/cdr")
@Tag(name = "CPOCdr")
public class CpoCdrController {

    protected final CpoCdrService cpoCdrService;
    protected final PaginationService paginationService;

    protected CpoCdrController(@Autowired CpoCdrService cpoCdrService,
                               @Autowired PaginationService paginationService) {
        this.cpoCdrService = cpoCdrService;
        this.paginationService = paginationService;
    }

    /**
     * Fetch CDRs from the CPO’s system.
     *
     * @param dateFrom Only return CDRs that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return CDRs that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET.
     * @return The endpoint returns a list of CDRs matching the given parameters in the GET request, the header
     * will contain the pagination related headers.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<CDRDto>>> getCdr(
            @RequestParam(value = "date_from") LocalDateTime dateFrom,
            @RequestParam(value = "date_to") LocalDateTime dateTo,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "limit") Integer limit,
            HttpServletRequest request) {
        int adjustedLimit = paginationService.adjustLimitByMax(limit);

        List<CDRDto> cdrs = cpoCdrService.getCdr(dateFrom, dateTo, offset, limit);
        long totalCount = cpoCdrService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<CDRDto>> responseFormat = new ResponseFormat<List<CDRDto>>()
                .build(OcpiStatusCode.SUCCESS, cdrs);
        HttpHeaders responseHeaders = paginationService.buildHeader(offset, adjustedLimit, request, totalCount);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }
}
