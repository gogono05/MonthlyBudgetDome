package com.example.demo.controller;

import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BudgetQuery;
import com.example.demo.model.BudgetResponse;
import com.example.demo.model.CheckMethod;
import com.example.demo.model.ResponseCode;
import com.example.demo.model.service.MonthlyBudgetService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MonthlyBudgetController {
	@Autowired
	MonthlyBudgetService monthlyBudgetService;

	@PostMapping("/queryBudget")
	public BudgetResponse queryBudget(@RequestBody BudgetQuery budgetQuery) {
		BudgetResponse budgetResponse;
		if (CheckMethod.formatCheck(budgetQuery)) {
			return new ResponseCode().errorResponse();// 查詢格式有誤
		}
		try {
			if (CheckMethod.dateCheck(budgetQuery)) {
				return new ResponseCode().DateErrorResponse();// 結束日早於開始日
			}
			budgetResponse = new ResponseCode().okResponse(monthlyBudgetService.getBudgetResponse(budgetQuery));
		} catch (DataAccessException e) {
			return new ResponseCode().DbErrorResponse();// 資料庫錯誤
		} catch (DateTimeParseException e) {
			return new ResponseCode().errorResponse();// 查詢格式有誤
		}

		return budgetResponse;// ok;

	}
}
