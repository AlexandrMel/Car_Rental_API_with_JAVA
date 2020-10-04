package com.example.demo.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

//Creating the database table model
@Entity(name = "vehicles")

public class VehicleEntity implements Serializable {

    private static final long serialVersionUID = 5512315223586710044L;

    // The automatic generated incrementing id for each entry
    @Id
    @GeneratedValue
    private long id;

    // Specifying using Column annotation the rules for each table column field
    @Column(nullable = false)
    private String vehicleId;
    @Column(nullable = false, length = 50)
    private String manufacturerName;
    @Column(nullable = false, length = 50)
    private String modelName;
    @Column(nullable = false, length = 4)
    private Integer productionYear;
    @Column(nullable = false, length = 4)
    private Double pricePerDay;
    @Column(nullable = false, length = 3)
    private Integer ageMin;
    @Column(nullable = false)
    private Boolean isActive;


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

// Getters and setters
