package ru.ifmo.mpi.magichospital.healer.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.healer.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.healer.domain.dto.HealerDTO;

@Service
public class HealerMapper {
	
	public HealerDTO toDTO(Healer healer) {
		HealerDTO dto = new HealerDTO();
		
		dto.setId(healer.getId());
    	dto.setName(healer.getName());
    	dto.setSurname(healer.getSurname());    	
    	dto.setMale(healer.isMale());
    	dto.setWorkStartDate(healer.getWorkStartDate());
    	dto.setSocialStatus(healer.getSocialStatus().getName());
    	dto.setHealerPower(healer.getHealerPower());
    	dto.setQueue(healer.getQueue());
    	
    	return dto;
	}

}
