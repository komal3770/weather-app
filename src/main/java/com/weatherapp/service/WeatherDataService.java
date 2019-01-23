package com.weatherapp.service;

import java.text.ParseException;
import java.util.Date;

import com.weatherapp.entity.WeatherData;

public interface WeatherDataService {
	WeatherData getWeatherByCityAndDate(Integer cityId, Date date) throws ParseException;
}
