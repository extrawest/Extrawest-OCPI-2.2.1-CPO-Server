package com.extrawest.ocpi.service.pagination;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public interface PaginationService {
    int adjustLimitByMax(Integer limit);

    HttpHeaders buildHeader(Integer offset, Integer limit, HttpServletRequest request, long totalCount);
}
