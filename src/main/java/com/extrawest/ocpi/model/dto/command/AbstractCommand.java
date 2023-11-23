package com.extrawest.ocpi.model.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public abstract class AbstractCommand {
    @JsonIgnore
    public String type;
    /**
     * URL that the CommandResult POST should be sent to. This URL might
     * contain unique ID to be able to distinguish between ReserveNow
     * requests.
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("response_url")
    protected String responseUrl;

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
