package com.weatherapp.service;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weatherapp.entity.City;
import com.weatherapp.entity.User;
import com.weatherapp.entity.UserCity;
import com.weatherapp.entity.WeatherData;
import com.weatherapp.repository.UserCityRepository;
import com.weatherapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Autowired
	private UserCityRepository userCityRepository;
	
	@Override
	public Optional<User> findById(Integer userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Map<String, Object> registerUser(String name, String email, String password, String mobile, Integer cityId) {
		Map<String,Object> resp = new HashMap<>();
		try{
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setMobile(mobile);
			City city = new City();
			city.setCityId(cityId);
			user.setCity(city);
			
			userRepository.save(user);
			
			resp.put("status", 1);
			resp.put("message", "Successfully Registered");
		}
		catch (ConstraintViolationException e) {
			resp.put("status", 0);
			List<String> errorFields = e.getConstraintViolations().stream().map(c -> c.getMessageTemplate()).collect(Collectors.toList());
			resp.put("message", "Failed : "+errorFields);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			resp.put("status", 0);
			resp.put("message", "Fail to Register");
		}
		return resp;
	}

	@Override
	public WeatherData getWeatherByUserCity(User user) throws ParseException {
		return weatherDataService.getWeatherByCityAndDate(user.getCity().getCityId(), new Date());
	}

	@Override
	public User getCurrentLoggedInUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal != null){
			User user = userRepository.findByEmail(principal.getName());
			return user;
		}
		return null;
	}

	@Override
	public WeatherData addCityForUser(Integer userId, Integer cityId) {
		
		try {
			WeatherData weather = weatherDataService.getWeatherByCityAndDate(cityId, new Date());
			if(weather!=null){
				UserCity userCity = new UserCity();
				userCity.getId().setCityId(cityId);
				userCity.getId().setUserId(userId);
				userCityRepository.save(userCity);
				return weather;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
