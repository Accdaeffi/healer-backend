package ru.ifmo.mpi.magichospital.healer.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;
import ru.ifmo.mpi.magichospital.healer.domain.dto.resource.ResourceDTO;

@Service
public class ResourceMapper {

	public ResourceDTO toDTO(ResourceDict resource) {
		ResourceDTO dto = new ResourceDTO();
		
		dto.setCode(resource.getCode());
		dto.setName(resource.getName());
    	
    	return dto;
	}
	
}
