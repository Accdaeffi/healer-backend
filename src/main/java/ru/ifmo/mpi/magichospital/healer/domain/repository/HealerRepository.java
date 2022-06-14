package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;

public interface HealerRepository extends JpaRepository<Healer, Integer> {

	Optional<Healer> findByLogin(String login);
}
