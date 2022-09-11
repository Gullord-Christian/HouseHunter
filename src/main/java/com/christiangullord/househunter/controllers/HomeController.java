package com.christiangullord.househunter.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.models.LoginUser;
import com.christiangullord.househunter.models.NoteModel;
import com.christiangullord.househunter.models.UserModel;
import com.christiangullord.househunter.services.HouseService;
import com.christiangullord.househunter.services.NoteService;
import com.christiangullord.househunter.services.UserService;

@MultipartConfig
@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private NoteService noteService;
	
	/// render reg/log-in form
	@GetMapping("/")
	public String index(Model model) {
		// create an empty user and loginUser for the jsp form
		model.addAttribute("newUser", new UserModel()); 
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	// process register logic
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") UserModel newUser, 
			BindingResult result, Model model, HttpSession session) {
		// this will call the register method and create a new user
			UserModel user = userService.register(newUser, result);
		if(result.hasErrors()) {
			// we need to make sure the page is able to re-render with the new login
			// register model (newUser) is already available
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		// store id from the db in the session
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	
	// process login logic
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, Model model, HttpSession session) {
		UserModel user = userService.login(newLogin, result);
		if(result.hasErrors() || user == null) {
			// making sure loginObject is already in place
			model.addAttribute("newUser", new UserModel());
			return "index.jsp";
		}
		// storing id from the database in the session
		session.setAttribute("userId", user.getId());
		session.setAttribute("firstName", user.getFirstName());
		session.setAttribute("lastName", user.getLastName());

		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
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
	
	// process book form
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
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		model.addAttribute("house", houseService.oneHouse(id));
		return "editHouse.jsp";
		}
	
	// process form
	@PutMapping("/houses/edit/{id}")
	public String editForm(@PathVariable("id") Long id, @Valid @ModelAttribute("house") HouseModel house, 
			BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.oneUser(userId));
	
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
		return "redirect:/dashboard";
	}
	
	@GetMapping("/housemarket")
	public String housemarket(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		model.addAttribute("user", userService.oneUser(userId));
		
		
		List<HouseModel> houseList = houseService.notMyHouses(userService.oneUser(userId));
		model.addAttribute("houses", houseList);
		
		List<HouseModel> myHouses = houseService.myHouses(userService.oneUser(userId));
		model.addAttribute("myHouses", myHouses);
		
		return "houseMarket.jsp";
	}
	
	@RequestMapping("/housemarket/{houseID}")
	public String saveHouse(@PathVariable("houseID") Long houseID, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		houseService.addHouseSaver(houseService.oneHouse(houseID), userService.oneUser(userId));
		return "redirect:/housemarket";
	}
	
	
	@RequestMapping("/housemarket/return/{houseID}")
	public String unsaveHouse(@PathVariable("houseID") Long houseID, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		houseService.removeHouseSaver(houseService.oneHouse(houseID));
		
		return "redirect:/housemarket";
	}
	
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
			return "notes.jsp";
		} else {
			NoteModel newNote = new NoteModel (note.getNote());
			newNote.setHouse(house);
			newNote.setCreator(userService.oneUser(userId));
			noteService.addNote(newNote);
			return "redirect:/houses/" + id + "/notes";
		}
		
	}
	
}