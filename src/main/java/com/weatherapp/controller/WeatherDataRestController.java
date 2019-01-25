package com.weatherapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.entity.City;
import com.weatherapp.service.CityService;
import com.weatherapp.service.WeatherDataService;
@RestController
public class WeatherDataRestController {
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/searchCity",method=RequestMethod.GET)
	public List<City> searchCity(@RequestParam String city){
		return cityService.searchCity(city);
	}
	
}
