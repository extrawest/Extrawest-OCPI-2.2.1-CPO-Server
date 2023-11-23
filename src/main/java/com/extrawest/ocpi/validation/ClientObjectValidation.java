package com.extrawest.ocpi.validation;

import com.extrawest.ocpi.exception.OcpiGeneralClientException;
import com.extrawest.ocpi.exception.OcpiInvalidParametersException;
import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.dto.location.EVSE;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class ClientObjectValidation {
    /**
     * @throws OcpiGeneralClientException     if country_code and/or party_id in request parameters is other than
     *                                        country_code and/or party_id in request body
     * @throws OcpiInvalidParametersException if tariff_id in request parameters is other than in request body
     */

    public static <T extends ClientOwnedObject> void checkClientCanModifyObject(T object,
                                                                                String countryCode,
                                                                                String partyId,
                                                                                String id) {
        if (!Objects.equals(object.getCountryCode(), countryCode)
                || !Objects.equals(object.getPartyId(), partyId))
            throw new OcpiGeneralClientException();

        if (!Objects.equals(object.getId(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

    public static void checkClientCanModifyObject(EVSE evse,
                                                  String id) {
        if (!Objects.equals(evse.getUid(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

    public static void checkClientCanModifyObject(Connector connector,
                                                  String id) {
        if (!Objects.equals(connector.getConnectorId(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

}
