package com.counsellor.entity;

import java.time.LocalDate;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "enq_tbl")
@Data
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String  stuName;
	private Long studentphno;
	private String courseName;
	private String 	classMode;
	private String enqStatus;
	private LocalDate createdDate;
	private LocalDate updateDate;
	
	@ManyToOne
	@JoinColumn(name="counsellorId")
	private Counsellor counsellor;
	
}
