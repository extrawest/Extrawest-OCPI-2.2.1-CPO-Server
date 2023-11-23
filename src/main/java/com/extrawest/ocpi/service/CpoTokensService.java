package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.extrawest.ocpi.model.enums.TokenType;

public interface CpoTokensService {

    TokenDto getToken(String countryCode, String partyId, String tokenUid, TokenType type);

    void putToken(TokenDto tokenDTO, String countryCode, String partyId, String tokenUid, TokenType type);

    void patchToken(TokenDto tokenDTO, String countryCode, String partyId, String tokenUid, TokenType type);

}
