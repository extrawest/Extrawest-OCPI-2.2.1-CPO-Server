package com.extrawest.ocpi.model.dto.cdr;

import com.extrawest.ocpi.model.enums.TokenType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CdrToken implements OcpiRequestData, OcpiResponseData {
    /**
     * ISO-3166 alpha-2 country code of the MSP that 'owns' this Token.
     */
    @JsonProperty("country_code")
    @NotBlank
    @Size(min = 2, max = 2)
    private String countryCode;
    /**
     * ID of the eMSP that 'owns' this Token (following the ISO-15118 standard).
     */
    @NotBlank
    @Size(min = 3, max = 3)
    @JsonProperty("party_id")
    private String partyId;

    /**
     * Unique ID by which this Token can be identified. This is the field used by the CPO’s system
     * (RFID reader on the Charge Point) to identify this token. Currently, in most cases: type=RFID,
     * this is the RFID hidden ID as read by the RFID reader, but that is not a requirement.
     * If this is a type=APP_USER Token, it will be a unique, by the eMSP, generated ID.
     */
    @JsonProperty("uid")
    @NotBlank
    @Size(max = 36)
    private String uid;

    /**
     * Type of the token
     */
    @JsonProperty("type")
    @NotNull
    private TokenType type;

    /**
     * Uniquely identifies the EV driver contract token within the eMSP’s platform (and suboperator platforms).
     * Recommended to follow the specification for eMA ID from "eMI3 standard version
     * V1.0" (<a href="http://emi3group.com/documents-links/">...</a>) "Part 2: business objects."
     */
    @JsonProperty("contract_id")
    @NotBlank
    @Size(max = 36)
    private String contractId;
}
