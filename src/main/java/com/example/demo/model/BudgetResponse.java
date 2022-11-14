package com.example.demo.model;

import java.util.List;

public class BudgetResponse {

	private int code;
	private String message;
	private List<MonthlyBudget> result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MonthlyBudget> getResult() {
		return result;
	}

	public void setResult(List<MonthlyBudget> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BudgetResponse [code=" + code + ", message=" + message + ", result=" + result + "]";
	}

	
}
