package ru.ifmo.mpi.magichospital.healer.domain.dto.resource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResourceDTO {

	List<ResourceDTO> resources;
}
