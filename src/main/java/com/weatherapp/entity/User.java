package com.weatherapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotNull(message="Name is required")
	@Size(max=255)
	@Column(length=255)
	private String name;
	
	@NotNull(message="Email is required")
	@Size(max=255)
	@Column(length=255)
	private String email;
	
	@NotNull(message="Password is required")
	@Size(max=100)
	@Column(length=100)
	private String password;
	
	@NotNull(message="Mobile is required")
	@Pattern(regexp="(^$|[0-9]{10})",message="Invalid Mobile")
	@Column(length=12)
	private String mobile;
	
	@NotNull(message="City is required")
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Transient
	private Integer cityId;
	
	//Getters & Setters
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + ", city=" + city + "]";
	}
}
