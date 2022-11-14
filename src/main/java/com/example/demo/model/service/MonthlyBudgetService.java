package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MonthlyBudget;
import com.example.demo.model.dao.MonthlyBudgetDao;

@Service
public class MonthlyBudgetService {

	@Autowired
	MonthlyBudgetDao monthlyBudgetDao;

	public List<MonthlyBudget> getBudgetResponse(LocalDate startLocalDate, LocalDate endLocalDate)  {

		String startMonth = keyToString(startLocalDate);
		String endMonth = keyToString(endLocalDate);

		List<MonthlyBudget> monthlyBudgetList = monthlyBudgetDao.getMonthlyBudgets2(startMonth, endMonth);
		Map<String, Integer> monthlyBudgetMap = monthlyBudgetList.stream()
				.collect(Collectors.toMap(MonthlyBudget::getMonth, MonthlyBudget::getBudget));
		List<MonthlyBudget> resMonthlyBudget = getResult(monthlyBudgetMap, startLocalDate, endLocalDate);

		int startDayOfMonth = startLocalDate.getDayOfMonth();// 取得start天數
		int endDayOfMonth = endLocalDate.getDayOfMonth();// 取得end天數

		int startDayBudget = resMonthlyBudget.get(0).getBudget() / startLocalDate.lengthOfMonth();// 計算start每日可用金額
		if (resMonthlyBudget.size() == 1) {// 如果list裡只有一個月份 則在這計算
			int monthlyBudget = endDayOfMonth - startDayOfMonth + 1;
			resMonthlyBudget.get(0).setBudget(monthlyBudget * startDayBudget);// ==============
			return resMonthlyBudget;
		}

		int startMonthBudget = startDayBudget * (startLocalDate.lengthOfMonth() - startDayOfMonth + 1);
		resMonthlyBudget.get(0).setBudget(startMonthBudget);// ================

		int endDayBudget = resMonthlyBudget.get(resMonthlyBudget.size() - 1).getBudget()
				/ endLocalDate.lengthOfMonth();// 計算end每日可用金額
		int endMonthBudget = endDayBudget * endDayOfMonth;// 計算end月份可用總金額
		resMonthlyBudget.get(resMonthlyBudget.size() - 1).setBudget(endMonthBudget);// ==================

		return resMonthlyBudget;

	}

	public List<MonthlyBudget> getResult(Map<String, Integer> monthlyBudgetMap, LocalDate startLocalDate,
			LocalDate endLocalDate)  {
		List<MonthlyBudget> resMonthlyBudget = new ArrayList<MonthlyBudget>();
		while (checkDate(startLocalDate, endLocalDate)) {
			String key = keyToString(startLocalDate);
			Integer value = monthlyBudgetMap.get(key);
			if (value == null) {
				MonthlyBudget monthlyBudget = new MonthlyBudget(key, 0);
				resMonthlyBudget.add(monthlyBudget);
			}else {
				resMonthlyBudget.add(new MonthlyBudget(key, value));
			}
			startLocalDate = startLocalDate.plusMonths(1);
		}
		return resMonthlyBudget;
	}

	private boolean checkDate(LocalDate startLocalDate, LocalDate endLocalDate) {
			return startLocalDate.isBefore(endLocalDate);
	}

	private String keyToString(LocalDate localDate) {
		return localDate.getYear() + String.format("%02d", localDate.getMonthValue());
	}

}
