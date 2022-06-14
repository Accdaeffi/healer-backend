package ru.ifmo.mpi.magichospital.healer.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Helper;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Request;
import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.RequestStatusDict;
import ru.ifmo.mpi.magichospital.healer.domain.dto.RequestDTO;
import ru.ifmo.mpi.magichospital.healer.domain.repository.HealerRepository;
import ru.ifmo.mpi.magichospital.healer.domain.repository.HelperRepository;
import ru.ifmo.mpi.magichospital.healer.domain.repository.RequestStatusRepository;

@Service
public class RequestMapper {

	@Autowired
	HealerRepository healerRepository;
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	RequestStatusRepository requestStatusRepository;
	
	@Autowired 
	RequestResourceMapper requestResourceMapper;
	
	public RequestDTO toDTO(Request request) {
		RequestDTO dto = new RequestDTO();
		dto.setDescription(request.getDescription());
		dto.setHealerId(request.getHealer().getId());
		dto.setHelperId(request.getHelper().getId());
		dto.setId(request.getId());
		dto.setRequestTime(request.getRequestTime());
		dto.setRequiredPentaHelp(request.getRequiredPentaHelp());
		dto.setStatus(request.getStatus().getCode());
		
		dto.setRequestedResources(
				request.getRequestedResources().stream()
    				.map(requestedResource -> requestResourceMapper.toDTO(requestedResource))
    				.collect(Collectors.toList())
    	);
		
		return dto;
	}
	
	public Request fromDTO(RequestDTO dto) {
		Helper helper = new Helper();
		
		if (dto.getHelperId() != 0) {
			helper = helperRepository.findById(dto.getHelperId()).get();
		} else {
			List<Helper> allHelpers = helperRepository.findAll();
			helper = allHelpers.get(new Random().nextInt(allHelpers.size()));
		}
		
		Optional<Healer> optionalHealer = healerRepository.findById(dto.getHealerId());
		
		Request request = new Request();
		request.setDescription(dto.getDescription());
		request.setRequestTime(dto.getRequestTime());
		request.setRequiredPentaHelp(dto.getRequiredPentaHelp());
		
		if (dto.getRequestedResources() != null) {
			request.setRequestedResources(
					dto.getRequestedResources().stream()
						.filter(requestedResource -> requestedResource.getAmount() > 0)
	    				.map(requestedResource -> {
	    					return requestResourceMapper.fromDTO(requestedResource).setRequest(request);
	    				})
	    				.collect(Collectors.toList())
	    	);
		} else {
			request.setRequestedResources(new ArrayList<>());
		}
		
		request.setHealer(optionalHealer.get());
		request.setHelper(helper);
		if (dto.getStatus() != null) {
			Optional<RequestStatusDict> optionalRequestStatusDict = requestStatusRepository.findByCode(dto.getStatus());
			request.setStatus(optionalRequestStatusDict.get());
		} else {
			request.setStatus(null);
		}
		
		return request;
	}
	
}
