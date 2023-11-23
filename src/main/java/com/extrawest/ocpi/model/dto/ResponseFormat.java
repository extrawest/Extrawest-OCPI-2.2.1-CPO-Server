package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The content that is sent with all the response messages is an 'application/json' type and contains a JSON object with
 * the following properties.
 * For errors on the HTTP layer, use HTTP error response codes, including the response format above,
 * that contains more details
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormat<T> {

    /**
     * OCPI status code, as listed in Status Codes {@link OcpiStatusCode}, indicates how the request was handled.
     * To avoid confusion with HTTP codes, OCPI status codes consist of four digits.
     */
    @JsonProperty("status_code")
    @NotNull
    @Min(value = 1000)
    @Max(value = 4999)
    private Integer statusCode;

    /**
     * An optional status message which may help when debugging.
     */
    @JsonProperty("status_message")
    private String statusMessage;

    /**
     * The time this message was generated.
     */
    @NotNull
    private LocalDateTime timestamp;

    /**
     * Contains the actual response data object or list of objects from each request
     */
    private T data;

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode) {
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode, T data) {
        this.setData(data);
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }
}
