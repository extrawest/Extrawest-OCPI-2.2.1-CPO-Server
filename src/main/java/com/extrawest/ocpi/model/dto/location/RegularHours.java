package com.extrawest.ocpi.model.dto.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Regular recurring operation or access hours.
 */
@Data
@NoArgsConstructor
public class RegularHours {
    /**
     * Number of day in the week, from Monday (1) till Sunday (7)
     */
    @NotNull
    @Min(1)
    @Max(7)
    @JsonProperty("weekday")
    private Integer weekday;
    /**
     * Begin of the regular period, in local time, given in hours and minutes. Must be in 24h format with leading zeros.
     * Example: "18:15". Hour/Minute separator: ":" Regex: ([0-1][0-9]|2[0-3]):[0-5][0-9].
     */
    @NotBlank
    @Size(max = 5)
    @JsonProperty("period_begin")
    private String periodBegin;
    /**
     * End of the regular period, in local time, syntax as for period_begin. Must be later than period_begin.
     */
    @NotBlank
    @Size(max = 5)
    @JsonProperty("period_end")
    private String periodEnd;
}
