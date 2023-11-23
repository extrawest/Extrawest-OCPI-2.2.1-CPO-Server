package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.enums.EnergySourceCategory;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnergySource implements OcpiRequestData, OcpiResponseData {
    /**
     * The type of energy source.
     */
    @JsonProperty("source")
    @NotNull
    private EnergySourceCategory source;

    /**
     * Percentage of this source (0-100) in the mix.
     */
    @JsonProperty("percentage")
    @NotNull
    @Min(0)
    @Max(100)
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float percentage;
}
