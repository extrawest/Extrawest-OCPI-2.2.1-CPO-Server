package com.extrawest.ocpi.model.dto.command;

import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.enums.CommandResultType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result of the command request, from the Charge Point
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandResult implements OcpiRequestData {

    /**
     * Result of the command request as sent by the Charge Point to the CPO.
     */
    @NotBlank
    @JsonProperty("result")
    private CommandResultType result;

    /**
     * Result of the command request as sent by the Charge Point to the CPO.
     */
    @JsonProperty("message")
    private DisplayText message;
}
