package com.bicimapa.prestamos.repoository;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.bicimapa.prestamos.model.Loan;
import com.bicimapa.prestamos.model.Person;
import com.bicimapa.prestamos.model.Stage;

import lombok.Data;

@Data
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
	
	private void buildNewLoan() { 
		Person recipient = Person.builder().name("Samuel Ayala").phone("3011234567").build();
		Person giver1 = Person.builder().name("Tía Claudia").phone("3011234567").build();
		Person giver2 = Person.builder().name("Jorge Ayala").phone("3011234567").build();
		Person giver3 = Person.builder().name("Mamuel").phone("3011234567").build();
		
		Loan loan = new Loan();
		loan.setGivers(Arrays.asList(giver1, giver2, giver3));
		loan.setStage(Stage.CREATED);
		loan.setRecipient(recipient);
		loan.setAmount(100000);
	}
}
