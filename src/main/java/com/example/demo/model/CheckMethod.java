package com.example.demo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CheckMethod {

	public static boolean formatCheck(BudgetQuery budgetQuery) {
		if (budgetQuery.getStartDate() == null || budgetQuery.getEndDate() == null
				|| budgetQuery.getStartDate().length() != 8 || budgetQuery.getEndDate().length() != 8) {
			return true;
		}
		try {
			getLocalDate(budgetQuery.getStartDate());
			getLocalDate(budgetQuery.getEndDate());
		
			return false;
		} catch (DateTimeParseException e) {
			return true;
		}
	}

	public static boolean dateCheck(BudgetQuery budgetQuery) {
		LocalDate stratLocalDate = getLocalDate(budgetQuery.getStartDate());
		LocalDate endLocalDate = getLocalDate(budgetQuery.getEndDate());
		if (stratLocalDate.isAfter(endLocalDate)) {
			return true;
		}
		return false;
	}

	private static LocalDate getLocalDate(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
}
