package com.bicimapa.prestamos.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class LoanCreation {
	
	private String reason;
	
	private int amount;
	
	private List<Person> givers;
	
	private LocalDate finalDate;
	
	private String story;
}
