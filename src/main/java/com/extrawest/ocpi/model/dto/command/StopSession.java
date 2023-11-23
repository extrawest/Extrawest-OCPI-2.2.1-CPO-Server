package com.extrawest.ocpi.model.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * StopSession object, for the STOP_SESSION command, with information needed to stop sessions.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StopSession extends AbstractCommand {

    /**
     * Session.id of the Session that is requested to be stopped
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("session_id")
    private String sessionId;
}