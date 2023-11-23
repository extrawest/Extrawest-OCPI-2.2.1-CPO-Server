package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.command.AbstractCommand;
import com.extrawest.ocpi.model.dto.command.CommandResponse;
import com.extrawest.ocpi.model.enums.CommandType;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoCommandsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cpo/api/2.2.1/commands")
@Tag(name = "CPOCommands")
@Validated
public class CpoCommandsController {

    protected final CpoCommandsService cpoCommandsService;

    protected CpoCommandsController(@Autowired CpoCommandsService cpoCommandsService) {
        this.cpoCommandsService = cpoCommandsService;
    }

    /**
     * Send a command to the CPO, requesting the CPO to send the command to the Charge Point
     *
     * @param command          Type of command that is requested.
     * @param requestedCommand Depending on the command parameter the body SHALL contain the applicable object
     *                         for that command.
     * @return Result of the command request, by the CPO (not the Charge Point). So this indicates if the CPO understood
     * the command request and was able to send it to the Charge Point. This is not the response by the Charge Point
     */
    @PostMapping("/{command}")
    public ResponseEntity<ResponseFormat<CommandResponse>> postCommand(
            @PathVariable(value = "command") CommandType command,
            @RequestBody @Valid AbstractCommand requestedCommand) {
        CommandResponse commandResponse = cpoCommandsService.postCommand(command, requestedCommand);

        ResponseFormat<CommandResponse> responseFormat = new ResponseFormat<CommandResponse>()
                .build(OcpiStatusCode.SUCCESS, commandResponse);
        return ResponseEntity.ok(responseFormat);
    }
}
