package com.extrawest.ocpi.validation;

import com.extrawest.ocpi.exception.OcpiGeneralClientException;
import com.extrawest.ocpi.exception.OcpiInvalidParametersException;
import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.dto.location.EVSE;
import com.extrawest.ocpi.model.dto.token.TokenDto;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class ClientObjectValidation {
    /**
     * @throws OcpiGeneralClientException     if country_code and/or party_id in request parameters is other than
     *                                        country_code and/or party_id in request body
     * @throws OcpiInvalidParametersException if tariff_id in request parameters is other than in request body
     */

    public static <T extends ClientOwnedObject> void checkClientCanModifyObject(TokenDto object,
                                                                                String countryCode,
                                                                                String partyId,
                                                                                String uid) {
        if (!Objects.equals(object.getCountryCode(), countryCode)
                || !Objects.equals(object.getPartyId(), partyId))
            throw new OcpiGeneralClientException();

        if (!Objects.equals(object.getUid(), uid)) {
            throw new OcpiInvalidParametersException();
        }
    }
}
