package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.extrawest.ocpi.model.enums.TokenType;

public interface CpoTokensService {

    TokenDto getToken(String countryCode, String partyId, String tokenUid, TokenType type);

    TokenDto putToken(TokenDto tokenDTO, String countryCode, String partyId, String tokenUid, TokenType type);

    TokenDto patchToken(TokenDto tokenDTO, String countryCode, String partyId, String tokenUid, TokenType type);

}
