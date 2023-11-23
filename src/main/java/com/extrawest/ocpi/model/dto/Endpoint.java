package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.InterfaceRole;
import com.extrawest.ocpi.model.enums.ModuleID;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Endpoint implements OcpiRequestData, OcpiResponseData {
    /**
     * Endpoint identifier.
     */
    @NotNull
    @JsonProperty("identifier")
    private ModuleID identifier;
    /**
     * Interface role this endpoint implements.
     */
    @NotNull
    @JsonProperty("role")
    private InterfaceRole role;
    /**
     * URL to the endpoint.
     */
    @NotBlank
    @JsonProperty("url")
    private String url;
}
