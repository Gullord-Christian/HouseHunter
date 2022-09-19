package com.christiangullord.househunter.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.christiangullord.househunter.models.HouseModel;
import com.christiangullord.househunter.services.HouseService;
import com.christiangullord.househunter.services.UserService;

@Controller
public class HouseMarketController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
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
}
