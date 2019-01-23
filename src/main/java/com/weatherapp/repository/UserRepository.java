package com.weatherapp.repository;

import org.springframework.stereotype.Repository;

import com.weatherapp.entity.User;
@Repository
public interface UserRepository extends BaseRepository<User, Integer>{
	
	User findByEmail(String email);
	
}
