package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.CdrDimensionType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CdrDimension implements OcpiRequestData {

    /**
     * Type of CDR dimension.
     */
    @JsonProperty("type")
    @NotNull
    private CdrDimensionType type;

    /**
     * Volume of the dimension consumed, measured according to the dimension type.
     */
    @NotNull
    @JsonProperty("volume")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float volume;
}
