package com.example.ReadingIsGood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadingIsGood.model.MonthlyStatisticsModel;
import com.example.ReadingIsGood.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	private StatisticsService statisticsService;

	public StatisticsController(StatisticsService statisticsService) {
		super();
		this.statisticsService = statisticsService;
	}

	@GetMapping("/getMonthlyStatistic/{customerId}")
	public List<MonthlyStatisticsModel> getMonthlyStatistic(@PathVariable("customerId") Long id) {
		return statisticsService.getMonthlyStatistic(id);
	}

}
