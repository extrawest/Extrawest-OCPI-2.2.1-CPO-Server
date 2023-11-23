package com.extrawest.ocpi.service;


import com.extrawest.ocpi.model.dto.cdr.CDRDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CpoCdrService {
    List<CDRDto> getCdr(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

}
