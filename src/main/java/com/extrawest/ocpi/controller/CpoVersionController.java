package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.VersionDetailsDto;
import com.extrawest.ocpi.model.dto.VersionDto;
import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.service.CpoVersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cpo/api/versions")
@Tag(name = "CpoVersion")
public class CpoVersionController {

    protected final CpoVersionService cpoVersionService;

    protected CpoVersionController(@Autowired CpoVersionService cpoVersionService) {
        this.cpoVersionService = cpoVersionService;
    }

    /**
     * Fetch information about the supported versions.
     *
     * @return list of VersionResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<VersionDto>> getVersion() {
        return ResponseEntity.ok(cpoVersionService.getVersion());
    }

    /**
     * Via the version details, the parties can exchange which modules are implemented for a specific version of OCPI,
     * which interface role is implemented, and what the endpoint URL is for this interface.
     *
     * @param version - version of OCPI
     * @return VersionDetails
     */
    @GetMapping("/details/{version}")
    public ResponseEntity<VersionDetailsDto> getVersionDetails(
            @PathVariable(value = "version") VersionNumber version
    ) {
        return ResponseEntity.ok(cpoVersionService.getVersionDetails(version));
    };
}
