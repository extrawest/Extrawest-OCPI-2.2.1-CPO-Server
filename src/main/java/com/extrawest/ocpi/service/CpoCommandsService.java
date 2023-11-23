package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.command.AbstractCommand;
import com.extrawest.ocpi.model.dto.command.CommandResponse;
import com.extrawest.ocpi.model.enums.CommandType;

public interface CpoCommandsService {

    CommandResponse postCommand(CommandType command, AbstractCommand requestedCommand);

}
