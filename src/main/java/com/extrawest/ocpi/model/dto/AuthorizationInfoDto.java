package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.dto.cdr.CDRDto;
import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.extrawest.ocpi.model.enums.AllowedType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains information about the authorization, if the Token is allowed to charge and optionally which
 * EVSEs are allowed to be used.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationInfoDto implements OcpiRequestData, OcpiResponseData {

    /**
     * Status of the Token, and whether charging is allowed at the optionally given location.
     */
    @NotNull
    @JsonProperty("allowed")
    private AllowedType allowed;

    /**
     * The complete Token object for which this authorization was requested.
     */
    @NotNull
    @JsonProperty("token")
    private TokenDto token;

    /**
     * Optional reference to the location if it was included in the request, and if the EV driver is allowed to charge
     * at that location. Only the EVSEs the EV driver is allowed to charge at are returned.
     */
    @JsonProperty("location")
    private LocationReferencesDto location;

    /**
     * Reference to the authorization given by the eMSP, when given, this reference will be provided in the relevant
     * {@link SessionDto} and/or {@link CDRDto}.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;

    /**
     * Optional display text, additional information to the EV driver.
     */
    @JsonProperty("info")
    private DisplayText info;
}
