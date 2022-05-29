package ru.ifmo.mpi.magichospital.healer.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request, String> {
	
}

