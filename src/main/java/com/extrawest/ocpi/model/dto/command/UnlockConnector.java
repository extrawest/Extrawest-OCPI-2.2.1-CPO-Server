package com.extrawest.ocpi.model.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UnlockConnector object, for the UNLOCK_CONNECTOR command, with information needed to unlock a connector
 * of a Charge Point.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UnlockConnector extends AbstractCommand {

    /**
     * Location.id of the Location (belonging to the CPO this request is send to) of which it is requested
     * to unlock the connector.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * EVSE.uid of the EVSE of this Location of which it is requested to unlock the connector.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("evse_uid")
    private String evseUid;

    /**
     * Connector.id of the Connector of this Location of which it is requested to unlock.
     */
    @NotBlank
    @Size(min = 1, max = 36)
    @JsonProperty("connector_id")
    private String connectorId;
}
