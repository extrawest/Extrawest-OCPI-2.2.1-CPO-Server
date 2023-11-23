package com.extrawest.ocpi.model.dto.charging_profile.results;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The ClearProfileResult object is send by the CPO to the given response_url in a POST request.
 * It contains the result of the DELETE (ClearProfile) request send by the eMSP
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ClearProfileResult extends AbstractProfileResult {

}
