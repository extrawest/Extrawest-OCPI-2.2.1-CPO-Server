package com.extrawest.ocpi.model.dto.command;

import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * StartSession object, for the START_SESSION command, with information needed to start a sessions.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StartSession extends AbstractCommand {

    /**
     * Token object the Charge Point has to use to start a new session. The
     * Token provided in this request is authorized by the eMSP.
     */
    @NotNull
    @JsonProperty("token")
    private TokenDto token;

    /**
     * Location.id of the Location (belonging to the CPO this request is send to) on which a session is to be started.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * Optional EVSE.uid of the EVSE of this Location on which a session is to be started. Required when connector_id
     * is set.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("evse_uid")
    private String evseUid;

    /**
     * Optional Connector.id of the Connector of the EVSE on which a session is to be started. This field is required
     * when the capability: START_SESSION_CONNECTOR_REQUIRED is set on the EVSE.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("connector_id ")
    private String connectorId;

    /**
     * Reference to the authorization given by the eMSP, when given, this reference will be provided in the relevant
     * Session and/or CDR.
     */
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;
}
