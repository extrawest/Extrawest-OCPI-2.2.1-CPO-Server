package com.extrawest.ocpi.service;


import com.extrawest.ocpi.model.dto.ClientInfoDto;

public interface ClientInfoService {

    ClientInfoDto getHubClientInfo(String countryCode, String partyId);

    void putHubClientInfo(String countryCode, String partyId);

}
