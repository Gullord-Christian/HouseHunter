package com.christiangullord.househunter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.models.UserModel;
import com.christiangullord.househunter.repositories.HouseRepo;

@Service
public class HouseService {
	@Autowired
	private final HouseRepo houseRepo;
	
	public HouseService (HouseRepo houseRepo) {
		this.houseRepo = houseRepo;
	}
	
	// find all
	public List<HouseModel> allHouses(){
		return houseRepo.findAll();
	}
	// find one
	public HouseModel oneHouse(Long id) {
		Optional<HouseModel> optionalHouse = houseRepo.findById(id);
		if(optionalHouse.isPresent()) {
			return optionalHouse.get();
		} else {
			return null;
		}
	}
	
	// create
	public HouseModel createHouse(HouseModel house) {
		return houseRepo.save(house);
	}
	// update
	public HouseModel updateHouse(HouseModel house) {
		return houseRepo.save(house);
	}
	//delete
	public void deleteHouse(Long id) {
		houseRepo.deleteById(id);
	}
	
	// remove house saver
	public void removeHouseSaver(HouseModel house) {
		house.setHouseSaver(null);
		houseRepo.save(house);
	}
	
	// add house saver
	public void addHouseSaver(HouseModel house, UserModel user) {
		house.setHouseSaver(user);
		houseRepo.save(house);
	}
	
	public List<HouseModel> notMyHouses(UserModel user){
		return houseRepo.findByHouseSaverIdIsOrUserIdIs(null, user.getId());
	}
	
	public List<HouseModel> myHouses(UserModel user){
		return houseRepo.findByHouseSaverIdIs(user.getId());
	}
	
	
}
