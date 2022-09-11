package com.christiangullord.househunter.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.christiangullord.househunter.models.HouseModel;
//import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.models.UserModel;

@Repository
public interface UserRepo extends CrudRepository <UserModel, Long>{
	Optional<UserModel> findByEmail(String email);
	List<UserModel> findAll();
	UserModel findByIdIs(Long id);
	List<UserModel> findBySavedHouses(HouseModel house);
	List<UserModel> findByHousesNotContains(HouseModel house);
	
}
