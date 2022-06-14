package ru.ifmo.mpi.magichospital.healer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;
import ru.ifmo.mpi.magichospital.healer.domain.dto.helper.ListHelperDTO;
import ru.ifmo.mpi.magichospital.healer.domain.dto.resource.ListResourceDTO;
import ru.ifmo.mpi.magichospital.healer.domain.dto.resource.ResourceDTO;
import ru.ifmo.mpi.magichospital.healer.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.healer.mappers.ResourceMapper;
import ru.ifmo.mpi.magichospital.healer.service.ResourceService;
import ru.ifmo.mpi.magichospital.healer.util.PathConstants;

@RestController
public class ResourceController {

	@Autowired 
	ResourceService resourceService;
	
	@Autowired
	ResourceMapper mapper;
	
	@Operation(summary = "Get list of resources")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get list", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ListHelperDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.HEALER_PREFIX+"/resources")
	public ListResourceDTO getResources() 
			throws PossibleSqlInjectionAttackException {	
		List<ResourceDTO> resources;
		
		resources = convertResourceListToResourceDTOList(resourceService.getResources());
		
		return new ListResourceDTO(resources);
	}
	
	private List<ResourceDTO> convertResourceListToResourceDTOList(List<ResourceDict> resources) {
		List<ResourceDTO> resourcesDTOs = resources.stream()
				.map(resource -> mapper.toDTO(resource))
				.collect(Collectors.toList());
			
		return resourcesDTOs;
	}
}
