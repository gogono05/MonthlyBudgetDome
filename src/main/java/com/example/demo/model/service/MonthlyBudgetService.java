package com.example.demo.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BudgetQuery;
import com.example.demo.model.MonthlyBudget;
import com.example.demo.model.dao.MonthlyBudgetDao;

@Service
public class MonthlyBudgetService {

	@Autowired
	MonthlyBudgetDao monthlyBudgetDao;

	public List<MonthlyBudget> getBudgetResponse(BudgetQuery budgetQuery) {
		List<MonthlyBudget>monthlyBudgetList = monthlyBudgetDao.getMonthlyBudgets(budgetQuery.getStartDate().substring(0,6),
																				   budgetQuery.getEndDate().substring(0,6));
		LocalDate stratLocalDate = LocalDate.parse(budgetQuery.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
		Integer startDayBudget = monthlyBudgetList.get(0).getBudget() / stratLocalDate.lengthOfMonth();
		if(monthlyBudgetList.size()==1) {
			Integer monthlyBudget = Integer.valueOf(budgetQuery.getEndDate().substring(6))-Integer.valueOf(budgetQuery.getStartDate().substring(6)) + 1;
			monthlyBudgetList.get(0).setBudget(monthlyBudget * startDayBudget);
			return monthlyBudgetList;
		}
		Integer startMonthBudget = startDayBudget * (stratLocalDate.lengthOfMonth() - Integer.valueOf(budgetQuery.getStartDate().substring(6)) + 1);
		monthlyBudgetList.get(0).setBudget(startMonthBudget);
		LocalDate endLocalDate = LocalDate.parse(budgetQuery.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
		Integer endDayBudget = monthlyBudgetList.get(monthlyBudgetList.size() - 1).getBudget() / endLocalDate.lengthOfMonth();
		Integer endMonthBudget = endDayBudget * Integer.valueOf(budgetQuery.getEndDate().substring(6));
		monthlyBudgetList.get(monthlyBudgetList.size() - 1).setBudget(endMonthBudget);
		
		return monthlyBudgetList;

	}

}
