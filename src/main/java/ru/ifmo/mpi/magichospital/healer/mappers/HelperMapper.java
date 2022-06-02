package ru.ifmo.mpi.magichospital.healer.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Helper;
import ru.ifmo.mpi.magichospital.healer.domain.dto.helper.HelperDTO;

@Service
public class HelperMapper {

	public HelperDTO toDTO(Helper helper) {
		HelperDTO dto = new HelperDTO();
		
		dto.setId(helper.getId());
    	dto.setName(helper.getName());
    	dto.setSurname(helper.getSurname());    	
    	dto.setMale(helper.isMale());
    	dto.setWorkStartDate(helper.getWorkStartDate());
    	
    	return dto;
	}
	
}
