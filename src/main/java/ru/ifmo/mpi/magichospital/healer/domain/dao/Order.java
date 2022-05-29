package ru.ifmo.mpi.magichospital.healer.domain.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import ru.ifmo.mpi.magichospital.healer.domain.dao.dict.ResourceDict;

@Entity
@Data
@Table(name = "order")
public class Order {
	
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(targetEntity = Request.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private Request request;
        
    @ManyToOne(targetEntity = ResourceDict.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private ResourceDict resource;
    
    @Column(name = "amount")
    private Integer amount;
    
    @Column(name = "received")
    private Boolean received;

}
