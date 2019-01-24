package com.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherapp.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateRepository stateRepository;
}
