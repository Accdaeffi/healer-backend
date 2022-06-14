package ru.ifmo.mpi.magichospital.healer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;
import ru.ifmo.mpi.magichospital.healer.domain.repository.ResourceDictRepository;

@Service
public class ResourceService {

	@Autowired
	ResourceDictRepository resourceDictRepository;
	
	public List<ResourceDict> getResources() {	
		List<ResourceDict> result = new ArrayList<>();
		resourceDictRepository.findAll().forEach(result::add);
		return result;
	}
}
