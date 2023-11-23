package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.ConnectionStatus;
import com.extrawest.ocpi.model.enums.Role;
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
public class ClientInfoDto {
    /**
     * CPO or eMSP ID of this party (following the 15118 ISO standard), as used in the credentials exchange.
     */
    @NotBlank
    @Size(min = 1, max = 3)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("party_id")
    private String partyId;

    /**
     * Country code of the country this party is operating in, as used in the credentials exchange.
     */
    @NotBlank
    @Size(min = 1, max = 2)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * The role of the connected party.
     */
    @NotNull
    @JsonProperty("role")
    private Role role;

    /**
     * Status of the connection to the party.
     */
    @NotNull
    @JsonProperty("status")
    private ConnectionStatus status;

    /**
     * Timestamp when this ClientInfo object was last updated.
     */
    @NotNull
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
