package com.weatherapp.repository;

import java.util.List;

import com.weatherapp.entity.City;

public interface CityRepository extends BaseRepository<City, Integer> {
	List<City> findByCityContainingOrderByCity(String city);
}
