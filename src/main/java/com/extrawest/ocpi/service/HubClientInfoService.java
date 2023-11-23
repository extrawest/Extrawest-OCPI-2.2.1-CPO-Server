package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.ClientInfoDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HubClientInfoService {

    List<ClientInfoDto> getClientInfoList(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

}
