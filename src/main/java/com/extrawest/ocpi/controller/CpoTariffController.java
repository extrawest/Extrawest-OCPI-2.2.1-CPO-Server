package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.PaginationHeaders;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.tariff.TariffDto;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoTariffService;
import com.extrawest.ocpi.util.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.extrawest.ocpi.util.PaginationUtils.OCPI_PAGINATION_LINK_HEADER;

@RestController
@RequestMapping("/cpo/api/2.2.1/tariffs")
@Tag(name = "CpoTariff")
@Validated
public class CpoTariffController {

    protected final CpoTariffService cpoTariffService;

    @Value("${maxXLimit}")
    private String maxXLimit;

    public CpoTariffController(@Autowired CpoTariffService cpoTariffService) {
        this.cpoTariffService = cpoTariffService;
    }

    /**
     * Returns Tariff objects from the CPO, last updated between the {date_from} and {date_to} (paginated)
     *
     * @param dateFrom Only return Tariffs that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return Tariffs that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET
     * @return List of all tariffs.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseFormat<List<TariffDto>>> getTariffs(
            @RequestParam(value = "date_from", required = false) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {

        limit = limit == null || limit > Integer.parseInt(maxXLimit) ? Integer.parseInt(maxXLimit) : limit;

        List<TariffDto> tariffs = cpoTariffService.getAll(dateFrom, dateTo, offset, limit);

        long totalCount = cpoTariffService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<TariffDto>> responseFormat = new ResponseFormat<List<TariffDto>>()
                .build(OcpiStatusCode.SUCCESS, tariffs);

        HttpHeaders responseHeaders = new HttpHeaders();
        if (PaginationUtils.hasNext(offset, limit, totalCount)) {
            String uriForNextPage = PaginationUtils.constructNextPageUri(request, offset, limit);
            responseHeaders.set(PaginationHeaders.LINK,
                    String.format(OCPI_PAGINATION_LINK_HEADER, uriForNextPage)
            );
        }

        responseHeaders.add(PaginationHeaders.X_LIMIT, maxXLimit);
        responseHeaders.add(PaginationHeaders.X_TOTAL_COUNT, String.valueOf(totalCount));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }

}
