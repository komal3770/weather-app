package com.weatherapp.service;

import java.util.List;

import com.weatherapp.entity.City;

public interface CityService {
	List<City> searchCity(String city);
}
