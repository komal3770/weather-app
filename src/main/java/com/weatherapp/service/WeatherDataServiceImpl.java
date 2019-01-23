package com.weatherapp.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherapp.entity.WeatherData;
import com.weatherapp.repository.WeatherDataRepository;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	@Autowired
	private WeatherDataRepository weatherDataRepository;

	@Override
	public WeatherData getWeatherByCityAndDate(Integer cityId, Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date formattedDate = formatter.parse(formatter.format(date));
		
		WeatherData weather = weatherDataRepository.findByCityCityIdAndDate(cityId,formattedDate);
		if(weather!=null){
			weather.setTempretureInFahrenheit(convertToFahrenheit(weather.getTempreture()));
		}
		return weather;
	}
	
	Double convertToFahrenheit(Double tempreture){
		return (tempreture * (9.0/5.0))+ 32;
	}
}
