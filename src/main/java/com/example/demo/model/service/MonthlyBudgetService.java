package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.MonthlyBudgetDao;

@Service
public class MonthlyBudgetService {

	@Autowired
	MonthlyBudgetDao monthlyBudgetDao;
	
	
}
