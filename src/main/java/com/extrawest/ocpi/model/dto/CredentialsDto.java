package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto implements OcpiRequestData, OcpiResponseData {
    /**
     * The credentials token for the other party to authenticate in your system.
     */
    @Size(min = 1, max = 255)
    @NotBlank
    private String token;

    /**
     * The URL to your API versions endpoint.
     */
    @NotBlank
    @Size(max = 255)
    private String url;

    /**
     * List of the roles this party provides.
     */
    @NotEmpty
    private List<CredentialsRole> roles;
}
