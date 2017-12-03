package com.bicimapa.prestamos.controllers;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(HeroPathController.class);
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
	    		model.addAttribute("progress", provider.getCurrentProgress());
	    		model.addAttribute("payments", provider.getCurrentLoan().getPayments());
	    		return "paying";
	    	} else if(Stage.FINISHED.equals(loan.getStage())) {
	    		return "finished";
	    	}
	    	return null;
    }
    
    @RequestMapping(path="/delete", method = RequestMethod.GET)
    public void deleteProgress() {
    		provider.setCurrentLoan(null);
    		logger.info("reseteamos todo");
    }

}
