package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;

@Repository
public interface ResourceDictRepository extends JpaRepository<ResourceDict, Integer>{
	Optional<ResourceDict> findByCode(String code);
}
