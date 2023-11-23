package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.dto.AbstractDomainObject;
import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.enums.Capability;
import com.extrawest.ocpi.model.enums.ParkingRestriction;
import com.extrawest.ocpi.model.enums.Status;
import com.extrawest.ocpi.model.markers.LocationData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The EVSE object describes the part that controls the power supply to a single EV in a single session.
 * It always belongs to a Location object. The object only contains directions to get from the location itself to the
 * EVSE (i.e. floor, physical_reference or directions).
 * When the directional properties of an EVSE are insufficient to reach the EVSE from the Location point,
 * then it typically indicates that the EVSE should be put in a different Location object
 * (sometimes with the same address but with different coordinates/directions).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EVSE extends AbstractDomainObject implements LocationData {
    /**
     * Uniquely identifies the EVSE within the CPOs platform (and sub-operator platforms). For example a
     * database ID or the actual "EVSE ID". This field can never be changed, modified or renamed.
     * This is the 'technical' identification of the EVSE, not to be used as 'human-readable' identification,
     * use the field evse_id for that. This field is named uid instead of id, because id could be confused
     * with evse_id which is an eMI3 defined field.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("uid")
    private String uid;
    /**
     * Compliant with the following specification for EVSE ID from "eMI3 standard version V1.0"
     * (<a href="http://emi3group.com/documents-links/"/>) "Part 2: business objects." Optional because: if an evse_id is
     * to be re-used in the real world, the evse_id can be removed from an EVSE object if the status is set to REMOVED.
     */
    @Size(max = 48)
    @JsonProperty("evse_id")
    private String evseId;
    /**
     * Indicates the current status of the EVSE.
     */
    @NotNull
    @JsonProperty("status")
    private Status status;
    /**
     * Indicates a planned status update of the EVSE.
     */
    @JsonProperty("status_schedule")
    private List<StatusSchedule> statusSchedule;
    /**
     * List of functionalities that the EVSE is capable of.
     */
    @JsonProperty("capabilities")
    private List<Capability> capabilities;
    /**
     * List of available connectors on the EVSE.
     */
    @NotEmpty
    @JsonProperty("connectors")
    private List<Connector> connectors;
    /**
     * Level on which the Charge Point is located (in garage buildings) in the
     * locally displayed numbering scheme.
     */
    @Size(max = 4)
    @JsonProperty("floor_level")
    private String floorLevel;
    /**
     * Coordinates of the EVSE.
     */
    @JsonProperty("coordinates")
    private GeoLocation coordinates;
    /**
     * A number/string printed on the outside of the EVSE for visual
     * identification.
     */
    @Size(max = 16)
    @JsonProperty("physical_reference")
    private String physicalReference;
    /**
     * Multi-language human-readable directions when more detailed information on how to reach the EVSE from
     * the Location is required.
     */
    @JsonProperty("directions")
    private List<DisplayText> directions;
    /**
     * The restrictions that apply to the parking spot.
     */
    @JsonProperty("parking_restrictions")
    private List<ParkingRestriction> parkingRestrictions;
    /**
     * Links to images related to the EVSE such as photos or logos.
     */
    @JsonProperty("images")
    private List<Image> images;
    /**
     * Timestamp when this EVSE or one of its Connectors was last updated (or created).
     */
    @NotNull
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
