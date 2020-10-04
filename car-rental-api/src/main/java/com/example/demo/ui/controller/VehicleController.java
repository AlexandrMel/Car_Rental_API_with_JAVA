package com.example.demo.ui.controller;

import com.example.demo.exceptions.UserServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.VehicleService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.shared.dto.VehicleDto;
import com.example.demo.ui.model.request.RequestOperationName;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.request.VehicleDetailsRequestModel;
import com.example.demo.ui.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Declaring Rest Controllers
@RestController
@RequestMapping("vehicles") // http:/localhost:8081/vehicles
public class VehicleController {

	@Autowired
	VehicleService vehicleService;
	@Autowired
	Utils utils;

	@GetMapping(path = "/{id}") // vehicles/:id
	public VehicleRest getVehicle(@PathVariable String id) {

		VehicleDto vehicleDto = vehicleService.getVehicleById(id);

		VehicleRest returnValue = new VehicleRest();
		BeanUtils.copyProperties(vehicleDto, returnValue);
		return returnValue;
	}

	@PostMapping
	/*
	 * createUser route controller is the main Method for creating the User, where
	 * userDetails is the incoming Body of the request to server and UserRest is the
	 * response that is send when the user is created
	 */

	public VehicleRest createVehicle(@RequestBody VehicleDetailsRequestModel vehicleDetails) throws Exception {
		if (vehicleDetails.getModelName() == null)
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		// Instantiate the UserRest model that contains predefined data field that will
		// be used to send back data
		VehicleRest returnValue = new VehicleRest();

		// Instantiate the general user Data transfer Object that will be shared between
		// layers
		VehicleDto vehicleDto = new VehicleDto();

		// Populate the user Data transfer Object with data received in the request
		// body(userDetails)
		BeanUtils.copyProperties(vehicleDetails, vehicleDto);

		// Trigger to start adding the data from the userDto to Database through
		// userService
		VehicleDto createdVehicle = vehicleService.createVehicle(vehicleDto);

		// Populate the returnValue with properties received from database as response
		// to user being created;
		BeanUtils.copyProperties(createdVehicle, returnValue);

		return returnValue;

	}

//Route controller for Updating user details, in out case only firstName and lastName possible, because the rest is sensible data
	@PutMapping(path = "/{id}") // vehicles/:id
	public VehicleRest updateVehicle(@PathVariable String id, @RequestBody VehicleDetailsRequestModel vehicleDetails) {

//Populating userDto with request body data
		VehicleDto vehicleDto = new VehicleDto();
		BeanUtils.copyProperties(vehicleDetails, vehicleDto);

//Getting the response from the service layer with updated data of the user from the database
		VehicleDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDto);

//Populating returnValue with the data received from  the service layer
		VehicleRest returnValue = new VehicleRest();
		BeanUtils.copyProperties(vehicleDto, returnValue);
		return returnValue;
	}

//Route controller for Deleting a User from the database

	@DeleteMapping(path = "/{id}") // vehicles/:id
	public OperationStatusModel deleteVehicle(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();

//There is no return statement here, we just send a custom response body with The method and Succes( should be improved and send back the data of the deleted user)
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		vehicleService.deleteVehicle(id);
		returnValue.setOperationResult(ResponseOperationStatus.SUCCESS.name());
		return returnValue;

	}

//Route controller to get a List of All Users with pagination and limit filter per page
	@GetMapping // users
	public List<VehicleRest> getVehicles(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		// Instantiate a List with specific type
		List<VehicleRest> returnValue = new ArrayList<VehicleRest>();

		// Get the list of users based on the page and limit from database through
		// service layer
		List<VehicleDto> vehicles = (List<VehicleDto>) vehicleService.getVehicles(page, limit);

		// Loop through the list to form an updated list with UserRest model
		for (VehicleDto vehicleDto : vehicles) {
			VehicleRest vehicleModel = new VehicleRest();
			BeanUtils.copyProperties(vehicleDto, vehicleModel);
			returnValue.add(vehicleModel);
		}
		return returnValue;
	}
	/*@GetMapping(path = "/available") // users
	public List<VehicleRest> getAvailableVehicles() {

		// Instantiate a List with specific type
		List<VehicleRest> returnValue = new ArrayList<>();

		// Get the list of users based on the page and limit from database through
		// service layer
		List<VehicleDto> vehicles = (List<VehicleDto>) vehicleService.getActiveVehicles();

		// Loop through the list to form an updated list with UserRest model
		for (VehicleDto vehicleDto : vehicles) {
			VehicleRest vehicleModel = new VehicleRest();
			BeanUtils.copyProperties(vehicleDto, vehicleModel);
			returnValue.add(vehicleModel);
		}
		return returnValue;
	}*/
}
