package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.Role;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsRole implements OcpiRequestData, OcpiResponseData {
    /**
     * Type of role.
     */
    @NotNull
    @JsonProperty("role")
    private Role role;
    /**
     * Details of this party.
     */
    @NotNull
    @JsonProperty("business_details")
    private BusinessDetails businessDetails;
    /**
     * CPO, eMSP (or other role) ID of this party (following the ISO-15118 standard).
     */
    @NotBlank
    @JsonProperty("party_id")
    private String partyId;
    /**
     * ISO-3166 alpha-2 country code of the country this party is operating in.
     */
    @NotBlank
    @JsonProperty("country_code")
    private String countryCode;
}
