package ru.ifmo.mpi.magichospital.healer.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.RequestResource;
import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;
import ru.ifmo.mpi.magichospital.healer.domain.dto.RequestResourceDTO;
import ru.ifmo.mpi.magichospital.healer.domain.repository.ResourceDictRepository;

@Service
public class RequestResourceMapper {

	@Autowired
	ResourceDictRepository resourceDictRepository;
	
	public RequestResourceDTO toDTO(RequestResource request) {
		RequestResourceDTO dto = new RequestResourceDTO();
		
		dto.setResourceCode(request.getResource().getCode());
		dto.setAmount(request.getAmount());
		
		return dto;
	}
	
	public RequestResource fromDTO(RequestResourceDTO dto) {
		Optional<ResourceDict> optionalResource = resourceDictRepository.findByCode(dto.getResourceCode());
		
		RequestResource resource = new RequestResource();
		
		resource.setAmount(dto.getAmount());
		resource.setResource(optionalResource.get());
		
		return resource;
	}
	
}
