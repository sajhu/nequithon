package com.bicimapa.prestamos.repoository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.bicimapa.prestamos.model.Loan;
import com.bicimapa.prestamos.model.Payment;
import com.bicimapa.prestamos.model.Person;
import com.bicimapa.prestamos.model.Stage;

import lombok.Setter;

@Setter
@Repository
@Scope("singleton")
public class LoanProvider {
	
	private Loan currentLoan;
	
	public Loan getCurrentLoan() {
		if(currentLoan == null) {
			buildNewLoan();
		}
		return currentLoan;
	}
	
	public int getCurrentProgress() {
		return getCurrentLoan().getPaid() * 100 / getCurrentLoan().getAmount();
	}
	
	public void definePayments() {
		currentLoan.setPeriods((int) Math.ceil((double)loanDays() / 7));
		ArrayList<Payment> payments = new ArrayList();
		int accumulate = 0;
		for (int i = 0; i < getCurrentLoan().getPeriods(); i++) {
			int value = currentLoan.getPaymentAmountForPeriod(i);
			accumulate += value;
			Payment payment = Payment.builder()
						.shouldPay(value)
						.wouldAccumulate(accumulate)
						.paid(0)
						.dueDate(ChronoTime.)
						.build();
			payments.add(payment);
		}
		currentLoan.setPayments(payments);
	}
	
	private void buildNewLoan() { 
		Person recipient = Person.builder().name("Samuel Ayala").phone("3011234567").build();
		Person giver1 = Person.builder().name("Tía Claudia").phone("3011234567").build();
		Person giver2 = Person.builder().name("Jorge Ayala").phone("3011234567").build();
		Person giver3 = Person.builder().name("Mamuel").phone("3011234567").build();
		
		currentLoan = new Loan();
		currentLoan.setGivers(Arrays.asList(giver1, giver2, giver3));
		currentLoan.setStage(Stage.PAYING);
		currentLoan.setRecipient(recipient);
		currentLoan.setAmount(100000);
		currentLoan.setPaid(22000);
		
		definePayments();
	}
	
	private int loanDays() {
		return (int) ChronoUnit.DAYS.between(currentLoan.getCreateDate(), currentLoan.getFinalDate());
	}
	
}
