package com.extrawest.ocpi.model.dto.cdr;

import com.extrawest.ocpi.model.markers.OcpiRequestData;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * This class contains all the information of the signed data. Which encoding method is used, if needed,
 * the public key and a list of signed values.
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SignedData implements OcpiRequestData, OcpiResponseData {

    /**
     * The name of the encoding used in the SignedData field. This is the name given to the encoding by a company
     * or group of companies. See note below.
     */
    @NotBlank
    @Size(max = 36)
    @JsonProperty("encoding_method")
    private String encodingMethod;

    /**
     * Version of the EncodingMethod (when applicable)
     */
    @JsonProperty("encoding_method_version")
    private Integer encodingMethodVersion;

    /**
     * Public key used to sign the data, base64 encoded.
     */
    @Size(max = 512)
    @JsonProperty("public_key")
    private String publicKey;

    /**
     * One or more signed values
     */
    @NotEmpty
    @JsonProperty("signed_values")
    private List<SignedValue> signedValues;

    /**
     * URL that can be shown to an EV driver. This URL gives the EV driver the possibility to check
     * the signed data from a charging session.
     */
    @Size(max = 512)
    @JsonProperty("url")
    private String url;

}
