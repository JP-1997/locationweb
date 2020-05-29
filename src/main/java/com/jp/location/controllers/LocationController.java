package com.jp.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.location.entities.Location;
import com.jp.location.repos.LocationRepository;
import com.jp.location.service.LocationService;
import com.jp.location.util.EmailUtil;
import com.jp.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	LocationService service;

	@Autowired
	LocationRepository repository;

	@Autowired
	ReportUtil reportUtil;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = service.saveLocation(location);
		String msg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("spring", "subject", "here is the body");
		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> allLocations = service.getAllLocations();
		modelMap.addAttribute("allLocations", allLocations);
		return "displayLocations";
	}

	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> allLocations = service.getAllLocations();
		modelMap.addAttribute("allLocations", allLocations);
		return "displayLocations";
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}

	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> allLocations = service.getAllLocations();
		modelMap.addAttribute("allLocations", allLocations);
		return "displayLocations";
	}

	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = sc.getRealPath("/");
		List<Object[]> data = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}
}
