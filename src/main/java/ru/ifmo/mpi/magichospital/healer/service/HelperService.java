package ru.ifmo.mpi.magichospital.healer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Helper;
import ru.ifmo.mpi.magichospital.healer.domain.repository.HelperRepository;
import ru.ifmo.mpi.magichospital.healer.exception.NotFoundException;
import ru.ifmo.mpi.magichospital.healer.exception.PossibleSqlInjectionAttackException;

@Service
public class HelperService {

	@Autowired
	HelperRepository helperRepository;
	
	/**
	 * Выдаёт список всех помощников
	 * 
	 * @return
	 */
	public List<Helper> getHelpers() {	
		List<Helper> result = new ArrayList<>();
		helperRepository.findAll().forEach(result::add);
		return result;
	}

	/**
	 * Выдаёт список помощников по строке поиска
	 * 
	 * @param searchString Строка, разбивающаяся на 2 токена по первому пробелу. Осуществляется как поиск по "имя фамилия", так и "фамилия имя"
	 * @return Список помощников, удовлетворяющий условию поиска
	 * @throws PossibleSqlInjectionAttackException Если в строке поиска есть SQL-инъекция
	 */
	public List<Helper> getHelpersByName(String searchString) 
			throws PossibleSqlInjectionAttackException {
		String[] tokens = searchString.split(" ", 2);
		
		String token1 = tokens[0];
		String token2 = tokens.length > 1 ? tokens[1] : "";
		
		if (!SqlSafeUtil.isSqlInjectionSafe(token1) || !SqlSafeUtil.isSqlInjectionSafe(token2)) {
            throw new PossibleSqlInjectionAttackException("Possible sql injection attack!");
        }

		
		token1 = "%"+token1+"%";	
		token2 = "%"+token2+"%";
		
		return helperRepository.findByTokens(token1, token2);
	}
	
	/**
	 * Выдаёт целителя по id
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public Helper getHelperById(int id) 
			throws NotFoundException {
		
		Optional<Helper> optionalHelper = helperRepository.findById(id);
		if (optionalHelper.isPresent()) {
			return optionalHelper.get();
		} else {
			throw new NotFoundException("No helper with such id!");
		}
		
	}
}
