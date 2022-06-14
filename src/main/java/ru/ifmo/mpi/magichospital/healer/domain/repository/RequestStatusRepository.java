package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.RequestStatusDict;

public interface RequestStatusRepository extends JpaRepository<RequestStatusDict, Integer>{
	Optional<RequestStatusDict> findByCode(String code);
}
