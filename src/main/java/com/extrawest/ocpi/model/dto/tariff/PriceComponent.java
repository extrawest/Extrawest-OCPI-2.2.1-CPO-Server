package com.extrawest.ocpi.model.dto.tariff;

import com.extrawest.ocpi.model.enums.TariffDimensionType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PriceComponent implements OcpiRequestData, OcpiResponseData {
    /**
     * Type of tariff dimension.
     */
    @NotNull
    private TariffDimensionType type;
    /**
     * Price per unit (excl. VAT) for this tariff dimension.
     */
    @NotNull
    @Positive
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float price;
    /**
     * Applicable VAT percentage for this tariff dimension. If omitted, no VAT is applicable.
     * Not providing a VAT is different from 0% VAT, which would be a value of 0.0 here.
     */
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float vat;
    /**
     * Minimum amount to be billed. This unit will be billed in this step_size blocks.
     * Amounts that are less than this step_size are rounded up to the given step_size.
     * For example: if type is TIME and step_size has a value of 300, then time will be billed in blocks of 5 minutes.
     * If 6 minutes were used, 10 minutes (2 blocks of step_size) will be billed.
     */
    @Positive
    private int step_size;
}
