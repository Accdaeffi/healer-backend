package ru.ifmo.mpi.magichospital.healer.domain.dto.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceDTO {
	
	private String name;
	private String code;
	private int maxAmount;
}
