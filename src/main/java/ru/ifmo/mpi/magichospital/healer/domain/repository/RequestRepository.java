package ru.ifmo.mpi.magichospital.healer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
	
}

