package com.example.demo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MonthlyBudget;

@Repository
public class MonthlyBudgetDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MonthlyBudget> getMonthlyBudgets(String startDate, String endDate) {
		final String sql = "select * from monthlybudget WHERE month between ? and ?  ";
		return jdbcTemplate.query(sql, new RowMapper<MonthlyBudget>() {
			@Override
			public MonthlyBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
				MonthlyBudget monthlyDudget = new MonthlyBudget();
				monthlyDudget.setBudget(rs.getInt("budget"));
				monthlyDudget.setMonth(rs.getString("month"));
				return monthlyDudget;
			}
		},startDate,endDate);
	}
	
	public List<MonthlyBudget> getMonthlyBudgets2(String startDate, String endDate) {
		final String sql = "select * from monthlybudget WHERE month between ? and ?  ";
		return jdbcTemplate.query(sql, this::row2Map,startDate,endDate);
	}
	
	private MonthlyBudget row2Map(ResultSet rs, int rowNum) throws SQLException  {
		MonthlyBudget monthlyDudget = new MonthlyBudget();
		monthlyDudget.setBudget(rs.getInt("budget"));
		monthlyDudget.setMonth(rs.getString("month"));
		return monthlyDudget;
		
	}
}