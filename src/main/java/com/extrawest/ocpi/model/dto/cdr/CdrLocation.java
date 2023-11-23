package com.extrawest.ocpi.model.dto.cdr;

import com.extrawest.ocpi.model.dto.location.GeoLocation;
import com.extrawest.ocpi.model.enums.ConnectorFormat;
import com.extrawest.ocpi.model.enums.ConnectorType;
import com.extrawest.ocpi.model.enums.PowerType;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CdrLocation implements OcpiRequestData, OcpiResponseData {
    /**
     * Uniquely identifies the location within the CPO’s platform (and sub-operator platforms). This field can
     * never be changed, modified or renamed.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("id")
    private String locationId;

    /**
     * Display name of the location.
     */
    @JsonProperty("name")
    @Size(max = 255)
    private String name;

    /**
     * Street/block name and house number if available.
     */
    @NotBlank
    @Size(max = 45)
    @JsonProperty("address")
    private String address;

    /**
     * City or town.
     */
    @NotBlank
    @Size(max = 45)
    @JsonProperty("city")
    private String city;

    /**
     * Postal code of the location, may only be omitted when the location has no postal code: in some countries
     * charging locations at highways don’t have postal codes.
     */
    @JsonProperty("postal_code")
    @Size(max = 10)
    private String postalCode;

    /**
     * State only to be used when relevant.
     */
    @JsonProperty("state")
    @Size(max = 20)
    private String state;

    /**
     * ISO 3166-1 alpha-3 code for the country of this location.
     */
    @JsonProperty("country")
    @NotBlank
    @Size(max = 3)
    private String country;

    /**
     * Coordinates of the location.
     */
    @JsonProperty("coordinates")
    @NotNull
    private GeoLocation coordinates;

    /**
     * Uniquely identifies the EVSE within the CPO’s platform (and suboperator platforms).
     * For example a database unique ID or the actual EVSE ID. This field can never be changed, modified or renamed.
     * This is the technical identification of the EVSE, not to be used as human-readable identification, use the field:
     * evse_id for that. Allowed to be set to: #NA when this CDR is created for a reservation that never resulted
     * in a charging session.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("evse_uid")
    private String evseUid;

    /**
     * Compliant with the following specification for EVSE ID from "eMI3 standard version V1.0"
     * (<a href="http://emi3group.com/documents-links/">...</a>) "Part 2: business objects.".
     * Allowed to be set to: #NA when this CDR is created for a reservation that never resulted in a charging session.
     */
    @NotBlank
    @Size(max = 48)
    @JsonProperty("evse_id")
    private String evseId;

    /**
     * Identifier of the connector within the EVSE. Allowed to be set to: #NA when this CDR is created
     * for a reservation that never resulted in a charging session.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("connector_id")
    private String connectorId;

    /**
     * The standard of the installed connector. When this CDR is created for a reservation that never resulted
     * in a charging session, this field can be set to any value and should be ignored by the Receiver.
     */
    @JsonProperty("connector_standard")
    private ConnectorType connectorStandard;

    /**
     * The format (socket/cable) of the installed connector. When this CDR is created for a reservation that
     * never resulted in a charging session, this field can be set to any value and should be ignored by the Receiver.
     */
    @JsonProperty("connector_format")
    private ConnectorFormat connectorFormat;

    /**
     * When this CDR is created for a reservation that never resulted in a charging session, this field can be set
     * to any value and should be ignored by the Receiver.
     */
    @JsonProperty("connector_power_type")
    private PowerType connectorPowerType;
}
