package com.christiangullord.househunter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.christiangullord.househunter.models.NoteModel;

@Repository
public interface NoteRepo extends CrudRepository<NoteModel, Long>{
	List<NoteModel> findAll();
	List<NoteModel> findByHouseIdIs(Long id);
}
