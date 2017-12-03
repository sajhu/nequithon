/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bicimapa.prestamos.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bicimapa.prestamos.delegates.LoanProvider;
import com.bicimapa.prestamos.model.LoanCreation;

@Controller
@RequestMapping("/")
public class LoanCreationController {

    private LoanProvider provider;

    @Autowired
    public LoanCreationController(LoanProvider provider) {
        this.provider = provider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("loanCreation", new LoanCreation());
        model.addAttribute("reasons", getReasons());
        return "form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model, 
                             @ModelAttribute("loanCreation") @Valid LoanCreation loanCreation,
                             BindingResult result) {
        System.out.println(result.getErrorCount());
        		provider.save(loanCreation);
        
        return "paying";
    }
    
    private ArrayList<String> getReasons() {
    	ArrayList<String> reasons = new ArrayList<>();
    	reasons.add("Completar la matrícula");
    	reasons.add("Emergencia médica");
    	reasons.add("Pagar el arriendo");
    	reasons.add("Pagar los servicios");
    	reasons.add("Otros");

    	return reasons;
    }
}
