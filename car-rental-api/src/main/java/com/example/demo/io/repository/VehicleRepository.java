package com.example.demo.io.repository;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//Instantiating UserRepository that by default will hold CRUD + Pagination operation methods for database manipulations
@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, Long> {

//Adding custom method that will check is the user with this email exists in the database
	VehicleEntity findByEmail(String email);

	VehicleEntity findByVehicleId(String userId);
}
