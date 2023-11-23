package com.extrawest.ocpi.model.dto.charging_profile.results;

import com.extrawest.ocpi.model.enums.ChargingProfileResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractProfileResult {

    @JsonIgnore
    public String type;
    @JsonProperty("result")
    protected ChargingProfileResultType result;

    public String getType() {
        return this.getClass().getSimpleName();
    }

}
