package ru.ifmo.mpi.magichospital.healer.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealerDTO {
	
	private Integer id;
	
    private String name;
    private String surname;
    
    @JsonProperty("isMale")
    private boolean isMale;
    private LocalDate workStartDate;
    
    private Integer healerPower;
    private Integer queue;
    
    private String socialStatus;

}
