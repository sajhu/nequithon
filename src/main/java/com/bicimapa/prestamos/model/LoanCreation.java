package com.bicimapa.prestamos.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LoanCreation {
	
	private String reason;
	
	private int amount;
	
	private List<Person> givers;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate finalDate;
	
	private String story;
}
