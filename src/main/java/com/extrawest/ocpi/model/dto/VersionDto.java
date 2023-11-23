package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersionDto implements OcpiResponseData, OcpiRequestData {
    /**
     * The version number
     */
    @NotNull
    public VersionNumber version;

    /**
     * URL to the endpoint containing version specific information
     */
    @NotBlank
    @Size(max = 255)
    public String url;
}
