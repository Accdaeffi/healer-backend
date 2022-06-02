package ru.ifmo.mpi.magichospital.healer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Helper;
import ru.ifmo.mpi.magichospital.healer.domain.dto.helper.HelperDTO;
import ru.ifmo.mpi.magichospital.healer.domain.dto.helper.ListHelperDTO;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;
import ru.ifmo.mpi.magichospital.healer.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.healer.mappers.HelperMapper;
import ru.ifmo.mpi.magichospital.healer.service.HelperService;
import ru.ifmo.mpi.magichospital.healer.util.PathConstants;

@RestController
public class HelperController {
	
	@Autowired 
	HelperService helperService;
	
	@Autowired
	HelperMapper mapper;
	
	@Operation(summary = "Get list of helpers")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get list", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ListHelperDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.HEALER_PREFIX+"/helpers")
	public ListHelperDTO getHelpers(@RequestParam(value="name", required = false) String searchString) 
			throws PossibleSqlInjectionAttackException {	
		List<HelperDTO> helpers;
		
		if (searchString == null) {
			helpers = convertHelperListToHelperDTOList(helperService.getHelpers());
		} else {
			helpers = convertHelperListToHelperDTOList(helperService.getHelpersByName(searchString));
		}
		
		return new ListHelperDTO(helpers);
	}
	
	@Operation(summary = "Get specific helper")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get helper", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = HelperDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No helper with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.HEALER_PREFIX+"/helper/{id}")
	public HelperDTO getHealer(@PathVariable int id) 
			throws NotFoundException {
		Helper helper = helperService.getHelperById(id);
		return mapper.toDTO(helper);
	}

	private List<HelperDTO> convertHelperListToHelperDTOList(List<Helper> helpers) {
		List<HelperDTO> helperDTOs = helpers.stream()
				.map(helper -> mapper.toDTO(helper))
				.collect(Collectors.toList());
			
		return helperDTOs;
	}
	
}
