package com.weatherapp.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.entity.User;
import com.weatherapp.entity.UserCity;
import com.weatherapp.entity.WeatherData;
import com.weatherapp.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(){
		return "Welcome";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Map<String, Object> register(@RequestBody User user){
		return userService.registerUser(user.getName(), user.getEmail(), user.getPassword(), user.getMobile(), user.getCityId());
	}
	
	@RequestMapping(value="/getWeatherByUser",method=RequestMethod.GET)
	public WeatherData getWeatherByUserCity(HttpServletRequest request) throws ParseException {
		User user = userService.getCurrentLoggedInUser(request);
		return userService.getWeatherByUserCity(user);
	}
	
	@RequestMapping(value="/getWeatherByUser",method=RequestMethod.POST)
	public WeatherData addCityForUser(HttpServletRequest request,UserCity userCity) throws ParseException {
		User user = userService.getCurrentLoggedInUser(request);
		return userService.addCityForUser(user.getUserId(), userCity.getCityId());
	}
	
	@RequestMapping("/logoutUser")
    public void logout(HttpServletRequest request) {
		System.out.println("Here comes");
    }
}
