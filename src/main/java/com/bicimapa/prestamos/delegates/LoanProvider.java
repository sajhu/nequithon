package com.bicimapa.prestamos.delegates;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.bicimapa.prestamos.model.Loan;
import com.bicimapa.prestamos.model.LoanCreation;
import com.bicimapa.prestamos.model.Payment;
import com.bicimapa.prestamos.model.Person;
import com.bicimapa.prestamos.model.Stage;

import lombok.Setter;

@Setter
@Repository
@Scope("singleton")
public class LoanProvider {
	
	private Logger logger = Logger.getLogger(LoanProvider.class);
	private Loan currentLoan;
	
	public Loan getCurrentLoan() {
		if(currentLoan == null) {
			buildNewLoan();
			logger.error("¡aguas, esa mierda está nula!");
		}
		
		return currentLoan;
	}
	
	public void save(LoanCreation loanCreation) {
		System.out.println("Loan creation request: " + loanCreation.toString());
		Person recipient = Person.builder().name("Samuel Ayala").phone("3011234567").build();
		Person giver1 = Person.builder().name("Tía Claudia").phone("3011234567").build();
		Person giver2 = Person.builder().name("Jorge Ayala").phone("3011234567").build();
		Person giver3 = Person.builder().name("Mamuel").phone("3011234567").build();
		
		currentLoan = new Loan();
		currentLoan.setReason(loanCreation.getReason());
		currentLoan.setStory(loanCreation.getStory());
		currentLoan.setGivers(Arrays.asList(giver1, giver2, giver3));
		currentLoan.setStage(Stage.PAYING);
		currentLoan.setRecipient(recipient);
		currentLoan.setAmount(loanCreation.getAmount());
		currentLoan.setPaid(33000);
		currentLoan.setCreateDate(LocalDate.now());
		currentLoan.setFinalDate(loanCreation.getFinalDate());

		definePayments();
	}
	
	public int getCurrentProgress() {
		return getCurrentLoan().getPaid() * 100 / getCurrentLoan().getAmount();
	}
	
	public ArrayList<Pair<String, String>> getCurrentMilestones() {
		ArrayList<Pair<String, String>> milestones = new ArrayList<>();
		milestones.add(new ImmutablePair<>("", "Conseguiste los "+ Utils.getPrettyAmount(currentLoan.getPayments().get(0).getShouldPay()) +" <span class=\"badge badge-success badge-pill\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i></span>"));
		milestones.add(new ImmutablePair<>("", "Conseguiste los "+ Utils.getPrettyAmount(currentLoan.getPayments().get(1).getShouldPay()) +" <span class=\"badge badge-success badge-pill\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i></span>"));
		milestones.add(new ImmutablePair<>("list-group-item-danger", "Recarga "+ Utils.getPrettyAmount(currentLoan.getPayments().get(2).getMissing()) +" para llegar a cumplir este reto <span class=\"badge badge-danger badge-pill\"><i class=\"fa fa-warning\" aria-hidden=\"true\"></i></span>"));
		milestones.add(new ImmutablePair<>("disabled", "<i class=\"fa fa-lock text-size-l\" aria-hidden=\"true\"></i> Paga la meta anterior primero"));
		milestones.add(new ImmutablePair<>("disabled text-size-l", "<i class=\"fa fa-lock\" aria-hidden=\"true\"></i>"));
		
		return milestones;
	}
	
	public void definePayments() {
		int loanDuration = loanDays();
		int interval = loanDuration / 5;
		currentLoan.setPeriods(5);
		LocalDate dueDate = LocalDate.now();
		ArrayList<Payment> payments = new ArrayList<>();
		int accumulate = 0;
		for (int i = 1; i <= getCurrentLoan().getPeriods(); i++) {
			int value = currentLoan.getPaymentAmountForPeriod(i);
			dueDate = dueDate.plusDays(interval);
			accumulate += value;
			Payment payment = Payment.builder()
						.shouldPay(value)
						.wouldAccumulate(accumulate)
						.paid(0)
						.dueDate(dueDate)
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
		currentLoan.setCreateDate(LocalDate.now());
		currentLoan.setFinalDate(LocalDate.now().plusDays(35));
		
		definePayments();
	}
	
	private int loanDays() {
		return (int) ChronoUnit.DAYS.between(currentLoan.getCreateDate(), currentLoan.getFinalDate());
	}
	
}
