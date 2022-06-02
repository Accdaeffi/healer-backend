package ru.ifmo.mpi.magichospital.healer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.healer.domain.dto.HealerDTO;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;
import ru.ifmo.mpi.magichospital.healer.mappers.HealerMapper;
import ru.ifmo.mpi.magichospital.healer.service.HealerService;
import ru.ifmo.mpi.magichospital.healer.util.PathConstants;

@RestController
public class HealerController {
	
	@Autowired 
	HealerService healerService;
	
	@Autowired
	HealerMapper mapper;
	
	@Operation(summary = "Get specific healer")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get healer", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = HealerDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No healer with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.HEALER_PREFIX+"/healer/{id}")
	public HealerDTO getHealer(@PathVariable int id) 
			throws NotFoundException {
		Healer healer = healerService.getHealerById(id);
		return mapper.toDTO(healer);
	}
	
}
