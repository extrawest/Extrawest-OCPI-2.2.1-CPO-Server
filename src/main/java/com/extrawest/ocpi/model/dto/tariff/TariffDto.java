package com.extrawest.ocpi.model.dto.tariff;

import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.dto.Price;
import com.extrawest.ocpi.model.dto.location.EnergyMix;
import com.extrawest.ocpi.model.enums.TariffType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TariffDto extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    /**
     * ISO-4217 code of the currency of this tariff.
     */
    @NotBlank
    @Size(min = 1, max = 3)
    private String currency;

    /**
     * Defines the type of the tariff. This allows for distinction in case of given Charging Preferences.
     * When omitted, this tariff is valid for all sessions.
     */
    private TariffType type;

    /**
     * List of multi-language alternative tariff info texts
     */
    @JsonProperty("tariff_alt_text")
    private List<DisplayText> tariffAltText;

    /**
     * List of multi-language alternative tariff info texts
     */
    @JsonProperty("tariff_alt_url")
    private String tariffAltUrl;

    /**
     * When this field is set, a Charging Session with this tariff will at least cost this
     * amount. This is different from a FLAT fee (Start Tariff, Transaction Fee), as a
     * FLAT fee is a fixed amount that has to be paid for any Charging Session. A
     * minimum price indicates that when the cost of a Charging Session is lower than
     * this amount, the cost of the Session will be equal to this amount.
     */
    @JsonProperty("min_price")
    private Price minPrice;

    /**
     * When this field is set, a Charging Session with this tariff will NOT cost more than
     * this amount.
     */
    @JsonProperty("max_price")
    private Price maxPrice;

    /**
     * List of Tariff Elements.
     */
    @NotEmpty
    private List<TariffElement> elements;

    /**
     * The time when this tariff becomes active, in UTC, time_zone field of the
     * Location can be used to convert to local time. Typically used for a new tariff that
     * is already given with the location, before it becomes active.
     */
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    /**
     * The time after which this tariff is no longer valid, in UTC, time_zone field if the
     * Location can be used to convert to local time. Typically used when this tariff is
     * going to be replaced with a different tariff in the near future. (See note below)
     */
    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;

    /**
     * Details on the energy supplied with this tariff.
     */
    @JsonProperty("energy_mix")
    private EnergyMix energyMix;
}
