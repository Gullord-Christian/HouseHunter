package com.christiangullord.househunter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.christiangullord.househunter.models.HouseModel;

@Repository
public interface HouseRepo extends CrudRepository <HouseModel, Long>{
	List<HouseModel> findAll();
	HouseModel findByIdIs(Long Id);
	List<HouseModel> findByHouseSaverIdIs(Long userId);
	List<HouseModel> findByHouseSaverIdIsOrUserIdIs(Long houseSaverID, Long userId);
}
