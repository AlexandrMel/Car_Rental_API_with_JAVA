package com.example.demo.service.impl;

import com.example.demo.exceptions.UserServiceException;
import com.example.demo.io.entity.VehicleEntity;
import com.example.demo.io.entity.VehicleEntity;
import com.example.demo.io.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.VehicleDto;
import com.example.demo.shared.dto.VehicleDto;
import com.example.demo.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

	// Importing UserRepositiry with its methods
	@Autowired
	VehicleRepository vehicleRepository;

//Importing Utils with its methods
	@Autowired
	Utils utils;


//Overriding  methods declared in UserService Class
	//Create New User in the database
	@Override
	public VehicleDto createVehicle(VehicleDto vehicle) {
		// using the custom created method of vehicleRepository class verify if the email
		// exists in the database and throw an exception with an explanatory message
		// Instantiating a VehicleEntity object from class and populating with the received
		// user data using Beans
		VehicleEntity vehicleEntity = new VehicleEntity();
		BeanUtils.copyProperties(vehicle, vehicleEntity);
		// Generate public UserId using custom created Utils class methods
		String publicVehicleId = utils.generateId(30);
		vehicleEntity.setActive(false);
		// Setting the new generated userId to the VehicleEntity object using a setter
		vehicleEntity.setVehicleId(publicVehicleId);
		// Hashing the password before storing into database using BCrypt external
		// package

		// Save the updated VehicleEntity to database using vehicleRepository Class default
		// method (save) and store the return value
		VehicleEntity storedVehicleDetails = vehicleRepository.save(vehicleEntity);
		// Instantiating a return Value object from VehicleDto class and populating with
		// the storedUserDetails data using Beans
		VehicleDto returnValue = new VehicleDto();
		BeanUtils.copyProperties(storedVehicleDetails, returnValue);

		// return
		return returnValue;
	}

// Method to get user details by username, in our case it is the email
/*
	@Override
	public VehicleDto getActiveVehicle(Boolean statement) {
		// Getting the data from the database
		VehicleEntity vehicleEntity = vehicleRepository.findByActive(statement);
		// Throw exception if not found
		if (vehicleEntity == null)
			throw new UsernameNotFoundException("No active vehicles");
		// Create populate and return user details object
		VehicleDto returnValue = new VehicleDto();
		BeanUtils.copyProperties(vehicleEntity, returnValue);
		return returnValue;
	}
*/


//Find a user in the database based on its UserId
	@Override
	public VehicleDto getVehicleById(String vehicleId) {
		// Getting the data from the database
		VehicleEntity vehicleEntity = vehicleRepository.findByVehicleId(vehicleId);
		// Throw exception if not found
		if (vehicleEntity == null)
			throw new UsernameNotFoundException("Vehicle with ID: " + vehicleId + " not found");
		// Create populate and return user details object
		VehicleDto returnValue = new VehicleDto();
		BeanUtils.copyProperties(vehicleEntity, returnValue);
		return returnValue;
	}
//Update a user entry in the database based on its UserId
	@Override
	public VehicleDto updateVehicle(String vehicleId, VehicleDto vehicle) {
		// Getting the data from the database
		VehicleEntity vehicleEntity = vehicleRepository.findByVehicleId(vehicleId);
		// Throw exception if not found
		if (vehicleEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		vehicleEntity.setManufacturerName(vehicle.getManufacturerName());
		vehicleEntity.setModelName(vehicle.getModelName());
		vehicleEntity.setProductionYear(vehicle.getProductionYear());
		vehicleEntity.setPricePerDay(vehicle.getPricePerDay());
		vehicleEntity.setAgeMin(vehicle.getAgeMin());

		VehicleEntity updatedVehicleDetails = vehicleRepository.save(vehicleEntity);
		VehicleDto returnValue = new VehicleDto();
		BeanUtils.copyProperties(updatedVehicleDetails, returnValue);
		return returnValue;
	}
//Delete a User from the Database
	@Override
	public void deleteVehicle(String vehicleId) {
		// Getting the data from the database
		VehicleEntity vehicleEntity = vehicleRepository.findByVehicleId(vehicleId);
		// Throw exception if not found
		if (vehicleEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		vehicleRepository.delete(vehicleEntity);

	}
//Get all Users from the database with pagination and a limit per page
	@Override
	public List<VehicleDto> getVehicles(int page, int limit) {
		List<VehicleDto> returnValue = new ArrayList<>();
//Fixing 0 index to page 1 for better user experience;
		if (page > 0)
			page = page - 1;
//Create Pageable object for findAll method
		Pageable pageableRequest = PageRequest.of(page, limit);
//Get List of Users based on the Pageable Object configuration
		Page<VehicleEntity> usersPage = vehicleRepository.findAll(pageableRequest);
		List<VehicleEntity> users = usersPage.getContent();
//Loop through the list to create returning Array of VehicleDto type
		for (VehicleEntity vehicleEntity : users) {
			VehicleDto vehicleDto = new VehicleDto();
			BeanUtils.copyProperties(vehicleEntity, vehicleDto);
			returnValue.add(vehicleDto);
		}
//Return the List
		return returnValue;
	}

	@Override
	public List<VehicleDto> getActiveVehicles() {
		List<VehicleDto> returnValue = new ArrayList<>();
Boolean statement = false;

//Get List of Users based on the Pageable Object configuration
		List<VehicleEntity> vehiclePage = vehicleRepository.findAllByActive(false);
		List<VehicleEntity> vehicle = vehiclePage.getContent();
//Loop through the list to create returning Array of VehicleDto type
		for (VehicleEntity vehicleEntity : users) {
			VehicleDto vehicleDto = new VehicleDto();
			BeanUtils.copyProperties(vehicleEntity, vehicleDto);
			returnValue.add(vehicleDto);
		}
//Return the List
		return returnValue;
	}
}
