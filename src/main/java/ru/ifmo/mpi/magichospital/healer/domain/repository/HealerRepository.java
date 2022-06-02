package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;

public interface HealerRepository extends CrudRepository<Healer, Integer> {

	Optional<Healer> findByLogin(String login);
}
