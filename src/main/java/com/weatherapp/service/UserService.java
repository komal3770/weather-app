package com.weatherapp.service;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.weatherapp.entity.User;
import com.weatherapp.entity.WeatherData;

public interface UserService {
	Optional<User> findById(Integer userId);
	
	Map<String,Object> registerUser(String name, String email, String password, String mobile, Integer cityId);
	
	WeatherData getWeatherByUserCity(User user) throws ParseException;
	
	User getCurrentLoggedInUser(HttpServletRequest request);
	
	WeatherData addCityForUser(Integer userId, Integer cityId);
}
