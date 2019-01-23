package com.weatherapp.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class WeatherData{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer weatherId;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@JsonProperty("tempretureInCelsius")
	private Double tempreture;
	
	private String description;
	
	private Date date;

	private Double tempretureInFahrenheit;
	
	public Integer getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Double getTempreture() {
		return tempreture;
	}

	public void setTempreture(Double tempreture) {
		this.tempreture = tempreture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTempretureInFahrenheit() {
		return tempretureInFahrenheit;
	}

	public void setTempretureInFahrenheit(Double tempretureInFahrenheit) {
		this.tempretureInFahrenheit = tempretureInFahrenheit;
	}
	
	
	
}
