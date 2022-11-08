package com.example.ReadingIsGood.service;

import java.util.List;

import com.example.ReadingIsGood.model.MonthlyStatisticsModel;

public interface StatisticsService {

	public List<MonthlyStatisticsModel> getMonthlyStatistic(Long customerId);
}
