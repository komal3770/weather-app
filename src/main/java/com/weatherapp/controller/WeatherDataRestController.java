package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.weatherapp.service.WeatherDataService;

public class WeatherDataRestController {
	@Autowired
	private WeatherDataService weatherDataService;
	
}
