package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.enums.Status;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This type is used to schedule status periods in the future. The eMSP can provide this information to the EV user
 * for trip planning purposes. A period MAY have no end. Example: "This station will be running as of tomorrow.
 * Today it is still planned and under construction."
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusSchedule implements OcpiResponseData, OcpiRequestData {

    /**
     * Begin of the scheduled period.
     */
    @NotNull
    @JsonProperty("period_begin")
    private LocalDateTime periodBegin;
    /**
     * End of the scheduled period, if known.
     */
    @JsonProperty("period_end")
    private LocalDateTime periodEnd;
    /**
     * Status value during the scheduled period.
     */
    @NotNull
    private Status status;
}
