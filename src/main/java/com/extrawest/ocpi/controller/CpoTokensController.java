package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.extrawest.ocpi.model.enums.TokenType;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoTokensService;
import com.extrawest.ocpi.validation.ClientObjectValidation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cpo/api/2.2.1/tokens")
@Tag(name = "CpoTokens")
@Validated
public class CpoTokensController {

    protected final CpoTokensService cpoTokensService;

    protected CpoTokensController(@Autowired CpoTokensService cpoTokensService) {
        this.cpoTokensService = cpoTokensService;
    }

    /**
     * Retrieve a Token as it is stored in the CPO system.
     *
     * @param countryCode Country code of the eMSP requesting this GET from the CPO system.
     * @param partyId     Country code of the eMSP requesting this GET from the CPO system.
     * @param tokenUid    Token.uid of the Token object to retrieve.
     * @param type        Token.type of the Token to retrieve. Default if omitted: RFID
     * @return The requested Token object.
     */
    @GetMapping("/{country_code}/{party_id}/{token_uid}/{type}")
    public ResponseEntity<ResponseFormat<TokenDto>> getToken(
            @PathVariable(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @PathVariable(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @PathVariable(value = "token_uid") @Size(min = 1, max = 36) String tokenUid,
            @PathVariable(value = "type", required = false) TokenType type
    ) {
        TokenDto dto = cpoTokensService.getToken(countryCode, partyId, tokenUid, type);

        ResponseFormat<TokenDto> responseFormat = new ResponseFormat<TokenDto>()
                .build(OcpiStatusCode.SUCCESS, dto);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Push new/updated Token object to the CPO.
     *
     * @param tokenDTO    New or updated Token object.
     * @param countryCode Country code of the eMSP sending this PUT request to the CPO system. This SHALL be the same value
     *                    as the country_code in the Token object being pushed.
     * @param partyId     Party ID (Provider ID) of the eMSP sending this PUT request to the CPO system.
     *                    This SHALL be the same value as the party_id in the Token object being pushed.
     * @param tokenUid    Token.uid of the (new) Token object (to replace).
     * @param type        Token.type of the Token of the (new) Token object (to replace). Default if omitted: RFID
     */
    @PutMapping("/{country_code}/{party_id}/{token_uid}/{type}")
    public ResponseEntity<ResponseFormat<TokenDto>> putToken(
            @RequestBody @Valid TokenDto tokenDTO,
            @PathVariable(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @PathVariable(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @PathVariable(value = "token_uid") @Size(min = 1, max = 36) String tokenUid,
            @PathVariable(value = "type", required = false) TokenType type) {

        ClientObjectValidation.checkClientCanModifyObject(tokenDTO, countryCode, partyId, tokenUid);
        TokenDto dto = cpoTokensService.putToken(tokenDTO, countryCode, partyId, tokenUid, type);

        ResponseFormat<TokenDto> responseFormat = new ResponseFormat<TokenDto>()
                .build(OcpiStatusCode.SUCCESS, dto);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Notify the CPO of partial updates to a Token.
     *
     * @param tokenDTO    New or updated Token object.
     * @param countryCode Country code of the eMSP sending this PUT request to the CPO system. This SHALL be the same value
     *                    as the country_code in the Token object being pushed.
     * @param partyId     Party ID (Provider ID) of the eMSP sending this PUT request to the CPO system.
     *                    This SHALL be the same value as the party_id in the Token object being pushed.
     * @param tokenUid    Token.uid of the (new) Token object (to replace).
     * @param type        Token.type of the Token of the (new) Token object (to replace). Default if omitted: RFID
     */
    @PatchMapping("/{country_code}/{party_id}/{token_uid}/{type}")
    public ResponseEntity<ResponseFormat<TokenDto>> patchToken(
            @RequestBody @Valid TokenDto tokenDTO,
            @PathVariable(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @PathVariable(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @PathVariable(value = "token_uid") @Size(min = 1, max = 36) String tokenUid,
            @PathVariable(value = "type", required = false) TokenType type) {

        ClientObjectValidation.checkClientCanModifyObject(tokenDTO, countryCode, partyId, tokenUid);
        TokenDto dto = cpoTokensService.patchToken(tokenDTO, countryCode, partyId, tokenUid, type);

        ResponseFormat<TokenDto> responseFormat = new ResponseFormat<TokenDto>()
                .build(OcpiStatusCode.SUCCESS, dto);
        return ResponseEntity.ok(responseFormat);
    }

}
