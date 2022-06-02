package ru.ifmo.mpi.magichospital.healer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.healer.domain.repository.HealerRepository;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;

@Service
public class HealerService {

	@Autowired
	HealerRepository healerRepository;
	
	public Healer getHealerById(int id) 
			throws NotFoundException {
		
		Optional<Healer> optionalHealer = healerRepository.findById(id);
		if (optionalHealer.isPresent()) {
			return optionalHealer.get();
		} else {
			throw new NotFoundException("No healer with such id!");
		}
		
	}
	
	public Healer getHealerByLogin(String login) 
			throws NotFoundException {
		Optional<Healer> optionalHealer = healerRepository.findByLogin(login);
		if (optionalHealer.isPresent()) {
			return optionalHealer.get();
		} else {
			throw new NotFoundException("No healer with such login!");
		}
	}

	
}
