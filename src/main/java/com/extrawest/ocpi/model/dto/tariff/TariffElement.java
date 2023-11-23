package com.extrawest.ocpi.model.dto.tariff;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class TariffElement implements OcpiRequestData, OcpiResponseData {
    /**
     * List of price components that describe the pricing of a tariff.
     */
    @NotEmpty
    @JsonProperty("price_components")
    private List<PriceComponent> priceComponents;
    /**
     * Restrictions that describe the applicability of a tariff.
     */
    private TariffRestrictions restrictions;
}
