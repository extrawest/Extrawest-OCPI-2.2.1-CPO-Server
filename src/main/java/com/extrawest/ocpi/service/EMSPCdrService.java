package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.CdrDTO;

public interface EMSPCdrService {

    CdrDTO getCdr(String id);
    String postCdr(CdrDTO cdrDTO);

}
