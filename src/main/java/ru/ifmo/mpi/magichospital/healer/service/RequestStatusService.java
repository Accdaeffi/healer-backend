package ru.ifmo.mpi.magichospital.healer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.RequestStatusDict;
import ru.ifmo.mpi.magichospital.healer.domain.repository.RequestStatusRepository;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;

@Service
public class RequestStatusService {

	@Autowired
	RequestStatusRepository requestStatusReporistory;
	
	public RequestStatusDict getRequestStatusByCode(String code) 
			throws NotFoundException {
		Optional<RequestStatusDict> optionalRequestStatus = requestStatusReporistory.findByCode(code);
		if (optionalRequestStatus.isPresent()) {
			return optionalRequestStatus.get();
		} else {
			throw new NotFoundException("No request status with such code!");
		}
	}
}
