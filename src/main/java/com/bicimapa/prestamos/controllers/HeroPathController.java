package com.bicimapa.prestamos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bicimapa.prestamos.model.Loan;
import com.bicimapa.prestamos.model.Stage;
import com.bicimapa.prestamos.repoository.LoanProvider;

@Controller
@RequestMapping("/heropath")
public class HeroPathController {

    private LoanProvider provider;

    @Autowired
    public HeroPathController(LoanProvider repository) {
        this.provider = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {

	    	Loan loan = provider.getCurrentLoan();
	    	model.addAttribute("giversSize", loan.getGivers().size());

	    	if(Stage.CREATED.equals(loan.getStage())) {
	    		return "created";
	    	} else if(Stage.GIVERS_SELECTED.equals(loan.getStage())) {
	    		return "selected";
	    	} else if(Stage.PAYING.equals(loan.getStage())) {
	    		return "paying";
	    	} else if(Stage.FINISHED.equals(loan.getStage())) {
	    		return "finished";
	    	}
	    	return null;
    }
    
    @RequestMapping(path="/delete", method = RequestMethod.GET)
    public void deleteProgress() {
    		provider.setCurrentLoan(null);
    }

}
