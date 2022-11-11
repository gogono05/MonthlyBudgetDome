package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MonthlyBudget;
import com.example.demo.model.service.MonthlyBudgetService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MonthlyBudgetController {

	@Autowired
	MonthlyBudgetService monthlyBudgetService;
	@PostMapping("/queryBudget")
	public MonthlyBudget queryBudget(@RequestBody String startDate ,String endDate) {
		LocalDate StartlocalDate = LocalDate.parse(startDate);
		LocalDate EndlocalDate = LocalDate.parse(endDate);
		if(StartlocalDate.isAfter(EndlocalDate)) {
			return null;
		}
		
		return null;
		
	}
	
}
