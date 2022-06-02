package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.RequestStatusDict;

public interface RequestStatusRepository extends CrudRepository<RequestStatusDict, Integer>{
	Optional<RequestStatusDict> findByCode(String code);
}
