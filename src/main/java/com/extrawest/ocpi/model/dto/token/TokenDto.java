package com.extrawest.ocpi.model.dto.token;

import com.extrawest.ocpi.model.enums.ProfileType;
import com.extrawest.ocpi.model.enums.TokenType;
import com.extrawest.ocpi.model.enums.WhitelistType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto implements OcpiResponseData, OcpiRequestData {
    /**
     * ISO-3166 alpha-2 country code of the MSP that 'owns' this Token.
     */
    @NotBlank
    @Size(min = 1, max = 2)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * ID of the eMSP that 'owns' this Token (following the ISO-15118 standard).
     */
    @NotBlank
    @Size(min = 1, max = 3)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("party_id")
    private String partyId;

    /**
     * Unique ID by which this Token, combined with the Token type, can be identified.
     * This is the field used by CPO system (RFID reader on the Charge Point) to identify this token.
     * Currently, in most cases: type=RFID, this is the RFID hidden ID as read by the RFID reader, but that is not
     * a requirement. If this is a APP_USER or AD_HOC_USER Token, it will be a uniquely, by the eMSP, generated ID.
     * This field is named uid instead of id to prevent confusion with: contract_id.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("uid")
    private String uid;

    /**
     * Type of the token
     */
    @NotNull
    @JsonProperty("type")
    private TokenType type;

    /**
     * Uniquely identifies the EV driver contract token within the eMSP’s platform (and suboperator platforms).
     * Recommended to follow the specification for eMA ID from "eMI3 standard version
     * V1.0" (<a href="http://emi3group.com/documents-links/">...</a>) "Part 2: business objects."
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("contract_id")
    private String contractId;

    /**
     * Visual readable number/identification as printed on the Token (RFID card), might be equal to the contract_id.
     */
    @Size(min = 1, max = 64)
    @JsonProperty("visual_number")
    private String visualNumber;

    /**
     * Issuing company, most of the time the name of the company printed on the token (RFID card), not necessarily the eMSP.
     */
    @NotBlank
    @Size(min = 1, max = 64)
    @JsonProperty("issuer")
    private String issuer;

    /**
     * This ID groups a couple of tokens. This can be used to make two or more tokens work as one,
     * so that a session can be started with one token and stopped with another, handy when a card and key-fob
     * are given to the EV-driver. Beware that OCPP 1.5/1.6 only support group_ids (it is called parentId in OCPP
     * 1.5/1.6) with a maximum length of 20.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("group_id")
    private String groupId;

    /**
     * Is this Token valid
     */
    @NotNull
    @JsonProperty("valid")
    private Boolean valid;

    /**
     * Indicates what type of white-listing is allowed.
     */
    @NotNull
    @JsonProperty("whitelist")
    private WhitelistType whitelist;

    /**
     * Language Code ISO 639-1. This optional field indicates the Token owner’s preferred interface language.
     * If the language is not provided or not supported then the CPO is free to choose its own language
     */
    @Size(min = 1, max = 2)
    @JsonProperty("language")
    private String language;

    /**
     * The default Charging Preference. When this is provided, and a charging session is started on an Charge Point
     * that support Preference base Smart Charging and support this ProfileType, the Charge Point can start using
     * this ProfileType, without this having to be set via: Set Charging Preferences.
     */
    @JsonProperty("default_profile_type")
    private ProfileType defaultProfileType;

    /**
     * When the Charge Point supports using your own energy supplier/contract at a Charge Point,
     * information about the energy supplier/contract is needed so the CPO knows which energy supplier to use.
     * NOTE: In a lot of countries it is currently not allowed/possible to use a drivers own energy supplier/contract
     * at a Charge Point.
     */
    @JsonProperty("energy_contract")
    private EnergyContract energyContract;

    /**
     * Timestamp when this Token was last updated (or created).
     */
    @NotNull
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
