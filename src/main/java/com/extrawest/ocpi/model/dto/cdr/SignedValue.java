package com.extrawest.ocpi.model.dto.cdr;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains the signed and the plain/unsigned data. By decoding the data, the receiver can check
 * if the content has not been altered.
 */
@Data
@NoArgsConstructor
public class SignedValue {

    /**
     * Nature of the value, in other words, the event this value belongs to.
     * Possible values at moment of writing:
     * - Start (value at the start of the Session)
     * - End (signed value at the end of the Session)
     * - Intermediate (signed values take during the Session, after Start, before End)
     * Others might be added later.
     */
    @NotBlank
    @JsonProperty("nature")
    @Size(max = 32)
    private String nature;

    /**
     * The un-encoded string of data. The format of the content depends on the EncodingMethod field.
     */
    @NotBlank
    @Size(max = 512)
    @JsonProperty("plain_data")
    private String plainData;

    /**
     * Blob of signed data, base64 encoded. The format of the content depends on the EncodingMethod field.
     */
    @NotBlank
    @Size(max = 5000)
    @JsonProperty("signed_data")
    private String signedData;
}
