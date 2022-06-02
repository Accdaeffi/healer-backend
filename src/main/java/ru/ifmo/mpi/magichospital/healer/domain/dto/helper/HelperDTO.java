package ru.ifmo.mpi.magichospital.healer.domain.dto.helper;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HelperDTO {

    private Integer id;
    
    private String name;
    private String surname;
    
    @JsonProperty("isMale")
    private boolean isMale;
    private LocalDate workStartDate;
}
