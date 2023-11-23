package com.extrawest.ocpi.model.dto.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Specifies one exceptional period for opening or access hours.
 */

@Data
@NoArgsConstructor
public class ExceptionalPeriod {
    /**
     * Begin of the exception. In UTC, time_zone field can be used to convert to local time.
     */
    @NotNull
    @JsonProperty("period_begin")
    private LocalDateTime periodBegin;
    /**
     * End of the exception. In UTC, time_zone field can be used to convert to local time.
     */
    @NotNull
    @JsonProperty("period_end")
    private LocalDateTime periodEnd;
}
