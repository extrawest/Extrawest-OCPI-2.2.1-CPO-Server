package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.tariff.TariffDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CpoTariffService {
    List<TariffDto> getAll(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);
}
