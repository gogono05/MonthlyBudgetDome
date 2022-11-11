package com.example.demo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@SuppressWarnings("all")
public class CheckMethod {

	public static boolean formatCheck(BudgetQuery budgetQuery) {
		try {
			LocalDate stratLocalDate = LocalDate.parse(budgetQuery.getStartDate(),DateTimeFormatter.ofPattern("yyyyMMdd"));
			LocalDate endLocalDate = LocalDate.parse(budgetQuery.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
		} catch (DateTimeParseException e) {
			return true;
		}
		if (budgetQuery.getStartDate() == null || budgetQuery.getEndDate() == null
				|| budgetQuery.getStartDate().length() != 8 || budgetQuery.getEndDate().length() != 8) {
			return true;
		}
		return false;
	}

	public static boolean dateCheck(BudgetQuery budgetQuery) {
		LocalDate stratLocalDate = LocalDate.parse(budgetQuery.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
		LocalDate endLocalDate = LocalDate.parse(budgetQuery.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));

		if (stratLocalDate.isAfter(endLocalDate)) {
			return true;
		}
		return false;
	}
}
