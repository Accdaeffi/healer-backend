package ru.ifmo.mpi.magichospital.healer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Request;
import ru.ifmo.mpi.magichospital.healer.domain.dto.RequestDTO;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;
import ru.ifmo.mpi.magichospital.healer.mappers.RequestMapper;
import ru.ifmo.mpi.magichospital.healer.service.RequestService;
import ru.ifmo.mpi.magichospital.healer.util.PathConstants;

@RestController
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	RequestMapper mapper;
	
	@Operation(summary = "Create new request to helpers")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Request created, returning request dto with id field", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = RequestDTO.class)) }),
			  @ApiResponse(responseCode = "401", description = "Trying register as another healer", 
			    content = @Content),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@PostMapping(PathConstants.API_PREFIX+PathConstants.HEALER_PREFIX+"/request")
	public RequestDTO addRequest(@RequestBody RequestDTO request, Principal loggedAdministrator) 
			throws NotFoundException {
		
		Request savedRequest = requestService.addRequest(request, loggedAdministrator.getName());
		return mapper.toDTO(savedRequest);
	}
}
