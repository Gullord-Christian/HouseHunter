package com.christiangullord.househunter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiangullord.househunter.models.NoteModel;
import com.christiangullord.househunter.repositories.NoteRepo;

@Service
public class NoteService {
	
	@Autowired
	private final NoteRepo noteRepo;
	
	public NoteService (NoteRepo noteRepo) {
		this.noteRepo = noteRepo;
	}
	
	public List<NoteModel> allNotes(){
		return noteRepo.findAll();
	}
	public List<NoteModel> houseNotes (Long houseId){
		return noteRepo.findByHouseIdIs(houseId);
	}
	public NoteModel addNote(NoteModel note) {
		return noteRepo.save(note);
	}
	public void deleteNote(Long noteID) {
		noteRepo.deleteById(noteID);
	}
	
}
