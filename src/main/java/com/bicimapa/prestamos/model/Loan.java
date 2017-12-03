package com.bicimapa.prestamos.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Loan {

	
	private Person recipient;
	
	private List<Person> givers;
	
	private Stage stage;
	
	private int amount;
	
	private int paid;
	
	private LocalDate createDate;
	
	private LocalDate finalDate;
	
	private int periods;
	
	private List<Payment> payments;
	
	public int getPaymentAmountForPeriod(int period) {
		return 2 * period * amount / (periods * (periods + 1));
	}
	
}
