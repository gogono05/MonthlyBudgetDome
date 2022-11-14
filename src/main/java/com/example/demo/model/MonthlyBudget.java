package com.example.demo.model;



public class MonthlyBudget {

	private String month;
	private Integer budget;
	
	public MonthlyBudget() {
	}

	public MonthlyBudget(String month, Integer budget) {
		this.month = month;
		this.budget = budget;
	}

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

	@Override
	public String toString() {
		return "MonthlyBudget [month=" + month + ", budget=" + budget + "]";
	}

}
