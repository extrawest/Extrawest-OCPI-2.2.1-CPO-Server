package com.extrawest.ocpi.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriComponentsBuilder;

@UtilityClass
public class PaginationUtils {
    public static final String OCPI_PAGINATION_LINK_HEADER = "<%s>; rel=\"next\"";

    public boolean hasNext(int offset, int limit, long totalCount) {
        return offset + limit < totalCount;
    }

    public String constructNextPageUri(HttpServletRequest request, int offset, int limit) {
        return UriComponentsBuilder
                .fromUriString(request.getRequestURL().toString())
                .query(request.getQueryString())
                .replaceQueryParam("offset", offset + limit)
                .encode()
                .toUriString();
    }
}
