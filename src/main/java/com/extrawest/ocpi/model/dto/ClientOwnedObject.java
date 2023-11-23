package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientOwnedObject implements OcpiResponseData {
    /**
     * ISO-3166 alpha-2 country code of the CPO that 'owns' this object (e.g. Session, Tariff, Cdr).
     */
    @NotBlank
    @Size(min = 1, max = 2)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * ID of the CPO that 'owns' this object (e.g. Session, Tariff, Cdr) (following the ISO-15118 standard).
     */
    @NotBlank
    @Size(min = 1, max = 3)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("party_id")
    private String partyId;

    /**
     * The unique id that identifies the object in the CPO/eMSP platform.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    private String id;

    /**
     * Timestamp when this object (e.g. Session, Tariff, Cdr) was last updated (or created).
     */
    @NotNull
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
