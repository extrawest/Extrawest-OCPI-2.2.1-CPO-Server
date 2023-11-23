package com.extrawest.ocpi.model.dto.token;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about a energy contract that belongs to a Token so a driver could use his/her own energy contract
 * when charging at a Charge Point.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyContract implements OcpiRequestData, OcpiResponseData {
    /**
     * Name of the energy supplier for this token.
     */
    @NotNull
    @Size(max = 61)
    @JsonProperty("supplier_name")
    private String supplierName;

    /**
     * Contract ID at the energy supplier, that belongs to the owner of this token.
     */
    @JsonProperty("contract_id")
    private String contractId;
}
