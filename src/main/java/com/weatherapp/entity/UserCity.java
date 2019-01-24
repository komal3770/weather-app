package com.weatherapp.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class UserCity {
	@EmbeddedId
	UserIdCityIdPK id;
	
	@Transient
	private Integer cityId;
	
	public UserCity() {
		this.id=new UserIdCityIdPK();
	}

	public UserIdCityIdPK getId() {
		return id;
	}

	public void setId(UserIdCityIdPK id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
