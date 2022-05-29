package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.LoginInfo;

@Repository
public interface LoginInfoRepository extends CrudRepository<LoginInfo, String> {
	Optional<LoginInfo> findByLogin(String login);
}
