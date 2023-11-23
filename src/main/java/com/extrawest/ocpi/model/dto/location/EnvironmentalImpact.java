package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.enums.EnvironmentalImpactCategory;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvironmentalImpact implements OcpiRequestData, OcpiResponseData {

    /**
     * The environmental impact category of this value.
     */
    @NotNull
    private EnvironmentalImpactCategory category;
    /**
     * Amount of this portion in g/kWh.
     */
    @NotNull
    @PositiveOrZero
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float amount;

}
