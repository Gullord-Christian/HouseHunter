package com.christiangullord.househunter.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.models.NoteModel;
import com.christiangullord.househunter.services.HouseService;
import com.christiangullord.househunter.services.NoteService;
import com.christiangullord.househunter.services.UserService;

@Controller
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping("/houses/{id}/notes")
	public String viewHouseNotes(@PathVariable("id") Long id, HttpSession session, Model model, 
			@ModelAttribute("newNote") NoteModel note) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.oneUser(userId));
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			HouseModel house = houseService.oneHouse(id);
			model.addAttribute("house", house);
			model.addAttribute("notes", noteService.houseNotes(id));
			return "notes.jsp";
	}
	
	@PostMapping("/houses/{id}/notes")
	public String newHouseNote(
			@PathVariable("id") Long id,
			HttpSession session,
			Model model,
			@Valid @ModelAttribute("newNote") NoteModel note,
			BindingResult result) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		HouseModel house = houseService.oneHouse(id);
		
		if(result.hasErrors()) {
			model.addAttribute("house", house);
			model.addAttribute("notes", noteService.houseNotes(id));
			model.addAttribute("user", userService.oneUser(userId));
			return "notes.jsp";
		} else {
			NoteModel newNote = new NoteModel (note.getNote());
			newNote.setHouse(house);
			newNote.setCreator(userService.oneUser(userId));
			noteService.addNote(newNote);
			return "redirect:/houses/" + id + "/notes";
		}
		
	}
	
	@DeleteMapping("/notes/delete/{id}")
	public String deleteNote(@PathVariable("noteID") Long noteID, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		noteService.deleteNote(noteID);
		return "notes.jsp";
	}
	
}
