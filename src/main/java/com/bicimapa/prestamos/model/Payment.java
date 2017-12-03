package com.bicimapa.prestamos.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
	
	private int shouldPay;
	
	private int wouldAccumulate;
	
	private int paid;
	
	private LocalDate dueDate;
}
