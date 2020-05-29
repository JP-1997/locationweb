package com.jp.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.location.entities.Location;
import com.jp.location.repos.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {
	
	@Autowired
	LocationRepository locationReposity;

	@GetMapping
	public List<Location> getLocations(){
		return locationReposity.findAll();
	}
	
	@PostMapping
	public Location createLocation(@RequestBody Location location) {
		return locationReposity.save(location);
	}
	
	@PutMapping
	public Location updateLocation(@RequestBody Location location) {
		return locationReposity.save(location);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		locationReposity.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable("id") int id) {
		return locationReposity.findById(id).get();
	}
}
