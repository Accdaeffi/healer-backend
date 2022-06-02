package ru.ifmo.mpi.magichospital.healer.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDTO {

    private Integer id;
    private Integer helperId;   
    private Integer healerId;
    private LocalDateTime requestTime;
    private Boolean requiredPentaHelp;
    private String description;  
    private String status;
}
