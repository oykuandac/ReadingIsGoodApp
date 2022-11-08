package com.example.ReadingIsGood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ReadingIsGood.model.MonthlyStatisticsModel;
import com.example.ReadingIsGood.repository.StatisticsRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private StatisticsRepository statisticsRepository;

	public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
		super();
		this.statisticsRepository = statisticsRepository;
	}

	public List<MonthlyStatisticsModel> getMonthlyStatistic(Long customerId) {
		List<Object> statistic = statisticsRepository.getMonthlyStatistic(customerId);
		List<MonthlyStatisticsModel> statistics = new ArrayList<>();
		for (Object obj : statistic) {
			MonthlyStatisticsModel model = new MonthlyStatisticsModel();
			Object[] objArr = (Object[]) obj;
			model.setMonth(objArr[0].toString());
			model.setTotalOrder(objArr[1].toString());
			model.setTotalBookCount(objArr[2].toString());
			model.setTotalPurchasedAmount(objArr[3].toString());
			statistics.add(model);
		}
		return statistics;
	}

}
