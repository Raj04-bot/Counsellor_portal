package com.counsellor.entity;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="counsellor")
@Data
public class Counsellor {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Integer counsellorId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	private String pswd;
	private Long phno;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updateDate;
}
