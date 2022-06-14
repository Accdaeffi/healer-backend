package ru.ifmo.mpi.magichospital.healer.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestResourceDTO {
	private String resourceCode;
	private int amount;
}
