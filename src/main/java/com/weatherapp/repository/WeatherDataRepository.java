package com.weatherapp.repository;

import java.util.Date;

import com.weatherapp.entity.WeatherData;

public interface WeatherDataRepository extends BaseRepository<WeatherData, Integer> {
	WeatherData findByCityCityIdAndDate(Integer cityId,Date date);
}
