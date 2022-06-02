package ru.ifmo.mpi.magichospital.healer.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Helper;

public interface HelperRepository extends CrudRepository<Helper, Integer> {

	@Query(value = "select * from helper h where (h.name ILIKE ?1 and (h.surname ILIKE ?2 or (h.surname IS NULL and '%%' = ?2))) or (h.name ILIKE ?2 and (h.surname ILIKE ?1 or (h.surname IS NULL and '%%' = ?1)))", 
			nativeQuery = true)
	List<Helper> findByTokens(String token1, String token2);
}
