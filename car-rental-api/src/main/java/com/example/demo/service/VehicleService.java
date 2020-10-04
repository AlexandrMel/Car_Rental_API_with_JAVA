package com.example.demo.service;

import com.example.demo.shared.dto.UserDto;
import com.example.demo.shared.dto.VehicleDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//Interface for VehicleService
public interface VehicleService {

//Default methods that need to be overridden
	VehicleDto createVehicle(VehicleDto vehicle);

	List<VehicleDto> getActiveVehicles();

	VehicleDto getVehicleById(String vehicleId);

	VehicleDto updateVehicle(String vehicleId, VehicleDto user);

	void deleteVehicle(String vehicleId);

	List<VehicleDto> getVehicles(int page, int limit);

}
