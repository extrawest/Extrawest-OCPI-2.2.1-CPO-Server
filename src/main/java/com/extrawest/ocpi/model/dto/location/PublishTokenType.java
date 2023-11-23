package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.enums.TokenType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Defines the set of values that identify a token to which a Location might be published.
 * At least one of the following fields SHALL be set: uid, visual_number, or group_id.
 * When uid is set, type SHALL also be set.
 * When visual_number is set, issuer SHALL also be set.
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PublishTokenType implements OcpiRequestData {

    /**
     * Unique ID by which this Token can be identified.
     */
    @Size(max = 36)
    @JsonProperty("uid")
    private String uid;
    /**
     * Type of the token.
     */
    @JsonProperty("type")
    private TokenType type;
    /**
     * Visual readable number/identification as printed on the Token (RFID card).
     */
    @Size(max = 64)
    @JsonProperty("visual_number")
    private String visualNumber;
    /**
     * Issuing company, most of the time the name of the company printed on the token (RFID card),
     * not necessarily the eMSP
     */
    @Size(max = 64)
    @JsonProperty("issuer")
    private String issuer;
    /**
     * This ID groups a couple of tokens. This can be used to make two or more tokens work as one.
     */
    @Size(max = 36)
    @JsonProperty("group_id")
    private String groupId;
}
