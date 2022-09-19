package com.christiangullord.househunter.controllers;


import java.util.Date;
import java.util.List;


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
import org.springframework.web.bind.annotation.PutMapping;


import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.services.HouseService;
import com.christiangullord.househunter.services.NoteService;
import com.christiangullord.househunter.services.UserService;


@Controller
public class HouseController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping("/dashboard")
	public String dashboard( HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		} 
		if (session.getAttribute("userId") == null)
			return "redirect:/logout";
		
		List<HouseModel> houseList = houseService.allHouses();
		model.addAttribute("user", userService.oneUser(userId));
		model.addAttribute("houses", houseList);
		return "dashboard.jsp";
	}
	
	@GetMapping("/houses/new")
	public String showHouseForm(@ModelAttribute("newHouse") HouseModel house, 
			HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.oneUser(userId));
		if(session.getAttribute("userId") == null ) {
				return "redirect:/logout";
		}
		
		return "houseForm.jsp";
	}
	
	// process the form
	@PostMapping("/houses/new")
	public String createHouse(@Valid @ModelAttribute("newHouse") HouseModel house, 
			BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
	
		if(userId == null) {
			return "redirect:/logout";
		}
		house.setListingDate(new Date());

		if(result.hasErrors()) {
			return "houseForm.jsp";
		} else {
			houseService.createHouse(new HouseModel(house.getAddress(), house.getPrice(), 
					house.getListingDate(), house.getBedrooms(), house.getBathrooms(), 
					house.getState(), house.getCity(), house.getSquarefootage(), 
					house.getZipcode(),
					userService.oneUser(userId)));
			return "redirect:/dashboard";
		} 
	}
	
	@GetMapping("/houses/{id}")
	public String showDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.oneUser(userId));
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		model.addAttribute("house", houseService.oneHouse(id));
		return "viewDetails.jsp";
	}
	
	@GetMapping("/houses/edit/{id}")
	public String showEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		model.addAttribute("house", houseService.oneHouse(id));
		model.addAttribute("user", userService.oneUser(userId));
		return "editHouse.jsp";
	}
	
	// process form
	@PutMapping("/houses/edit/{id}")
	public String editForm(@PathVariable("id") Long id, @Valid @ModelAttribute("house") HouseModel house, 
			BindingResult result, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "editHouse.jsp";
		} else {
			houseService.updateHouse(house);
			
			return "redirect:/housemarket";
		}
	}

	@DeleteMapping("/houses/delete/{id}")
	public String deleteHouse(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		houseService.deleteHouse(id);
		return "redirect:/housemarket";
	}
	
	
}