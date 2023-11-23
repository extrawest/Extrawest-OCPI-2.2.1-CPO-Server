package com.extrawest.ocpi.service.pagination;

import com.extrawest.ocpi.model.dto.PaginationHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Value("${maxXLimit}")
    private String maxXLimit;

    public static final String OCPI_PAGINATION_LINK_HEADER = "<%s>; rel=\"next\"";

    @Override
    public int adjustLimitByMax(Integer limit) {
        limit = limit == null || limit > Integer.parseInt(maxXLimit) ? Integer.parseInt(maxXLimit) : limit;
        return limit;
    }

    @Override
    public HttpHeaders buildHeader(Integer offset, Integer limit, HttpServletRequest request, long totalCount) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if (hasNext(offset, limit, totalCount)) {
            String uriForNextPage = constructNextPageUri(request, offset, limit);
            responseHeaders.set(PaginationHeaders.LINK, String.format(OCPI_PAGINATION_LINK_HEADER, uriForNextPage));
        }

        responseHeaders.add(PaginationHeaders.X_LIMIT, maxXLimit);
        responseHeaders.add(PaginationHeaders.X_TOTAL_COUNT, String.valueOf(totalCount));
        return responseHeaders;
    }

    private boolean hasNext(int offset, int limit, long totalCount) {
        return offset + limit < totalCount;
    }

    private String constructNextPageUri(HttpServletRequest request, int offset, int limit) {
        return UriComponentsBuilder
                .fromUriString(request.getRequestURL().toString())
                .query(request.getQueryString())
                .replaceQueryParam("offset", offset + limit)
                .encode()
                .toUriString();
    }
}
