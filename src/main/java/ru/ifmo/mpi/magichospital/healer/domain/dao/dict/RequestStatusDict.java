package ru.ifmo.mpi.magichospital.healer.domain.dao.dict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "request_status_dict")
public class RequestStatusDict {
	
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(name = "name")
	private String name;
    
	@Column(name = "code")
	private String code;
	
	public static enum Statuses {
		NEW
	}

}
