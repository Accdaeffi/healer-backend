package ru.ifmo.mpi.magichospital.healer.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.healer.domain.dao.Request;
import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.RequestStatusDict;
import ru.ifmo.mpi.magichospital.healer.domain.dto.RequestDTO;
import ru.ifmo.mpi.magichospital.healer.domain.repository.RequestRepository;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;
import ru.ifmo.mpi.magichospital.healer.mappers.RequestMapper;

@Service
public class RequestService {
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RequestStatusService requestStatusService;

	@Autowired
	RequestMapper requestMapper;
	
	@Autowired 
	HealerService healerService;
	
	/**
	 * Добавление в БД новый запрос о помощи у помощников
	 * 
	 * @param requestDTO Описание запроса
	 * @param loggedHealer От чьего имени делается запрос
	 * @return Сохранённый в БД запрос
	 * @throws NotFoundException Если какое-то поле некорректное и соответсвующий ему объект не найден в БД
	 */
	public Request addRequest(RequestDTO requestDTO, String loggedHealer) 
			throws NotFoundException {
		
		try {
			Healer healer = healerService.getHealerByLogin(loggedHealer);
			
			Request request = requestMapper.fromDTO(requestDTO);
			request.setId(0);
			
			request.setHealer(healer);
			
			if (request.getRequestTime() == null) {
				request.setRequestTime(LocalDateTime.now());
			}
				
			request.setStatus(requestStatusService.getRequestStatusByCode(RequestStatusDict.Statuses.NEW.toString().toLowerCase()));
					
			request = requestRepository.save(request);
			return request;

		}
		catch (NoSuchElementException ex) 
		{
			ex.printStackTrace();
			System.out.println(ex);
			throw new NotFoundException("No healer, helper or status with such id/code!");
		}
	}

}
