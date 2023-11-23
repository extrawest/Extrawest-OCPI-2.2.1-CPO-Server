package com.extrawest.ocpi.model.dto.command;

import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.enums.CommandResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommandResponse {

    /**
     * Response from the CPO on the command request.
     */
    @NotNull
    @JsonProperty("result")
    private CommandResponseType result;

    /**
     * Timeout for this command in seconds. When the Result is not received within this timeout,
     * the eMSP can assume that the message might never be sent.
     */
    @NotNull
    @JsonProperty("timeout")
    private Integer timeout;

    /**
     * Human-readable description of the result (if one can be provided), multiple languages can be provided.
     */
    @JsonProperty("message")
    private List<DisplayText> message;
}
