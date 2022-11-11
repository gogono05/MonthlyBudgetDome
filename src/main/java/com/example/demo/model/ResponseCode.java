package com.example.demo.model;

import java.util.List;

public class ResponseCode {
	public BudgetResponse okResponse(List<MonthlyBudget> monthlyBudgetList) {
		BudgetResponse budgetResponse= new BudgetResponse();
		budgetResponse.setCode(0);
		budgetResponse.setMessage("查詢成功");
		budgetResponse.setResult(monthlyBudgetList);
		return budgetResponse;
	}

	public BudgetResponse errorResponse() {
		BudgetResponse budgetResponse= new BudgetResponse();
		budgetResponse.setCode(-10);
		budgetResponse.setMessage("查詢格式有誤");
		budgetResponse.setResult(null);
		return budgetResponse;
	}
	
	public BudgetResponse DateErrorResponse() {
		BudgetResponse budgetResponse= new BudgetResponse();
		budgetResponse.setCode(-11);
		budgetResponse.setMessage("結束日早於開始日");
		budgetResponse.setResult(null);
		return budgetResponse;
	}
	
	public BudgetResponse DbErrorResponse() {
		BudgetResponse budgetResponse= new BudgetResponse();
		budgetResponse.setCode(-100);
		budgetResponse.setMessage("資料庫錯誤");
		budgetResponse.setResult(null);
		return budgetResponse;
	}
}
