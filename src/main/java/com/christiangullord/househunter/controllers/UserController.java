package com.christiangullord.househunter.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.christiangullord.househunter.models.LoginUser;
import com.christiangullord.househunter.models.UserModel;
import com.christiangullord.househunter.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/// render reg/log-in form
	@GetMapping("/")
	public String index(Model model) {
		// create an empty user and loginUser for the jsp form
		model.addAttribute("newUser", new UserModel()); 
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@GetMapping("/register")
	public String registerNew() {
		return "redirect:/";
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
			return "error.jsp";
		}
		
		// store id from the db in the session
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/login")
	public String loginNew() {
		return "redirect:/";
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
}
