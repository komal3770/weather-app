package com.weatherapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherapp.entity.City;
import com.weatherapp.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> searchCity(String city) {
		// TODO Auto-generated method stub
		return cityRepository.findByCityContainingOrderByCity(city);
	}
}
