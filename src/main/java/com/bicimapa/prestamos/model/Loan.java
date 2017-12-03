package com.bicimapa.prestamos.model;

import java.util.List;

import lombok.Data;

@Data
public class Loan {

	
	private Person recipient;
	
	private List<Person> givers;
	
	private Stage stage;
	
	private int amount;
	
	public int getPaymentAmountForPeriod(int period) {
		return 2 * period * amount / (getPeriods() * (getPeriods() + 1));
	}
	
	public int getPeriods() {
		return givers.size();
	}
}
