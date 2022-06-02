package ru.ifmo.mpi.magichospital.healer.mappers;

import java.util.Optional;

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
	
	public RequestDTO toDTO(Request request) {
		RequestDTO dto = new RequestDTO();
		dto.setDescription(request.getDescription());
		dto.setHealerId(request.getHealer().getId());
		dto.setHelperId(request.getHelper().getId());
		dto.setId(request.getId());
		dto.setRequestTime(request.getRequestTime());
		dto.setRequiredPentaHelp(request.getRequiredPentaHelp());
		dto.setStatus(request.getStatus().getCode());
		
		return dto;
	}
	
	public Request fromDTO(RequestDTO dto) {
		Optional<Healer> optionalHealer = healerRepository.findById(dto.getHealerId());
		Optional<Helper> optionalHelper = helperRepository.findById(dto.getHelperId());
		
		Request request = new Request();
		request.setDescription(dto.getDescription());
		request.setRequestTime(dto.getRequestTime());
		request.setRequiredPentaHelp(dto.getRequiredPentaHelp());
		
		request.setHealer(optionalHealer.get());
		request.setHelper(optionalHelper.get());
		if (dto.getStatus() != null) {
			Optional<RequestStatusDict> optionalRequestStatusDict = requestStatusRepository.findByCode(dto.getStatus());
			request.setStatus(optionalRequestStatusDict.get());
		} else {
			request.setStatus(null);
		}
		
		return request;
	}
	
}
