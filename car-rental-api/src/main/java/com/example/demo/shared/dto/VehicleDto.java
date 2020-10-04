package com.example.demo.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;

//User Data Transfer Object Model, used to pass the data throughout the project, has all the possible fields needed

public class VehicleDto implements Serializable {

	private static final long serialVersionUID = 4865903039190150223L;
	private long id;
	private String vehicleId;
	private String manufacturerName;
	private String modelName;
	private Integer productionYear;
	private Double pricePerDay;
	private Integer ageMin;
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}




	
	
	
	
}
