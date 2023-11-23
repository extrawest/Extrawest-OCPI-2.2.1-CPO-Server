package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EnergyMix implements OcpiRequestData, OcpiResponseData {

    /**
     * True if 100% from regenerative sources. (CO2 and nuclear waste is zero)
     */
    @NotNull
    @JsonProperty("is_green_energy")
    private boolean is_green_energy;
    /**
     * Key-value pairs (enum + percentage) of energy sources of this location’s tariff.
     */
    private List<EnergySource> energy_sources;
    /**
     * Key-value pairs (enum + percentage) of nuclear waste and CO2 exhaust of this location’s tariff.
     */
    private List<EnvironmentalImpact> environ_impact;
    /**
     * Name of the energy supplier, delivering the energy for this location or tariff.
     */
    private String supplier_name;
    /**
     * Name of the energy suppliers product/tariff plan used at this location.
     */
    private String energy_product_name;

}
