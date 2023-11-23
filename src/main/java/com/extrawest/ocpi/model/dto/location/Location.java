package com.extrawest.ocpi.model.dto.location;

import com.extrawest.ocpi.model.dto.BusinessDetails;
import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.dto.DisplayText;
import com.extrawest.ocpi.model.enums.Facility;
import com.extrawest.ocpi.model.enums.ParkingType;
import com.extrawest.ocpi.model.markers.LocationData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Location extends ClientOwnedObject implements LocationData {
    /**
     * Defines if a Location may be published on an website or app etc.
     * When this is set to false, only tokens identified in the field: publish_allowed_to are allowed to be shown
     * this Location. When the same location has EVSEs that may be published and may not be published, two 'Locations'
     * should be created.
     */
    @NotNull
    private Boolean publish;

    /**
     * This field may only be used when the published field is set to false. Only owners of Tokens that match all the set
     * fields of one PublishToken in the list are allowed to be shown this location.
     */
    @JsonProperty("publish_allowed_to")
    private List<PublishTokenType> publishAllowedTo;

    /**
     * Display name of the location.
     */
    @Size(min = 1, max = 255)
    private String name;

    /**
     * Street/block name and house number if available.
     */
    @NotBlank
    @Size(min = 1, max = 45)
    private String address;

    /**
     * City or town.
     */
    @NotBlank
    @Size(min = 1, max = 45)
    private String city;

    /**
     * Postal code of the location, may only be omitted when the location has no postal code: in some countries charging
     * locations at highways don’t have postal codes.
     */
    @Size(min = 1, max = 10)
    @JsonProperty("postal_code")
    private String postalCode;

    /**
     * State or province of the location, only to be used when relevant.
     */
    @Size(min = 1, max = 20)
    private String state;

    /**
     * ISO 3166-1 alpha-3 code for the country of this location.
     */
    @NotBlank
    @Size(min = 1, max = 3)
    private String country;

    /**
     * Coordinates of the location.
     */
    @NotNull
    private GeoLocation coordinates;

    /**
     * Geographical location of related points relevant to the user.
     */
    @JsonProperty("related_locations")
    private List<AdditionalGeoLocation> relatedLocations;

    /**
     * The general type of parking at the charge point location.
     */
    @JsonProperty("parking_type")
    private ParkingType parkingType;

    /**
     * List of EVSEs that belong to this Location.
     */
    private List<EVSE> evses;

    /**
     * Human-readable directions on how to reach the location.
     */
    private List<DisplayText> directions;

    /**
     * Information of the operator. When not specified, the information retrieved from the Credentials module,
     * selected by the country_code and party_id of this Location, should be used instead.
     */
    private BusinessDetails operator;

    /**
     * Information of the suboperator if available.
     */
    @JsonProperty("suboperator")
    private BusinessDetails subOperator;

    /**
     * Information of the owner if available.
     */
    private BusinessDetails owner;

    /**
     * Optional list of facilities this charging location directly belongs to.
     */
    private List<Facility> facilities;

    /**
     * One of IANA tzdata’s TZ-values representing the time zone of
     * the location. Examples: "Europe/Oslo", "Europe/Zurich".
     * (@see a href="http://www.iana.org/time-zones">Time Zones</a>)
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("time_zone")
    private String timeZone;

    /**
     * The times when the EVSEs at the location can be accessed for charging.
     */
    @JsonProperty("opening_times")
    private Hours openingTimes;

    /**
     * Indicates if the EVSEs are still charging outside the opening hours of the location. E.g. when the parking garage
     * closes its barriers over-night, is it allowed to charge till the next morning?
     * Default: true
     */
    @JsonProperty("charging_when_closed")
    private Boolean chargingWhenClosed;

    /**
     * Links to images related to the location such as photos or logos.
     */
    private List<Image> images;

    /**
     * Details on the energy supplied at this location.
     */
    @JsonProperty("energy_mix")
    private EnergyMix energyMix;
}
