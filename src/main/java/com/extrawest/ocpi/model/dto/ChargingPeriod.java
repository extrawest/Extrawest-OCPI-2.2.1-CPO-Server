package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A Charging Period consists of a start timestamp and a list of possible values that influence this period,
 * for example: amount of energy charged this period, maximum current during this period etc.
 */
@Data
@NoArgsConstructor
public class ChargingPeriod implements OcpiRequestData, OcpiResponseData {

    /**
     * Start timestamp of the charging period. A period ends when the next period starts.
     * The last period ends when the session ends.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("start_date_time")
    @NotNull
    private LocalDateTime startDateTime;

    /**
     * List of relevant values for this charging period.
     */
    @NotEmpty
    @JsonProperty("dimensions")
    private List<CdrDimension> dimensions;

    /**
     * Unique identifier of the Tariff that is relevant for this Charging Period. If not provided,
     * no Tariff is relevant during this period.
     */
    @JsonProperty("tariff_id")
    private String tariffId;

}
