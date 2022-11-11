package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class MonthlyBudget {

	private String month;
	private Integer budget;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

}
