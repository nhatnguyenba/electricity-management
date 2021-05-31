package com.electricitymanagement.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.electricitymanagement.repository.*;
import com.electricitymanagement.model.*;

@RestController
@RequestMapping(path="/admin", produces = "application/json")
@CrossOrigin(origins = "*")
public class AdminController {
	private final AdminRepository adminRepository;

	@Autowired
	public AdminController(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Admin postAdmin(@RequestBody Admin admin) {
		return adminRepository.save(admin);
	}
	
	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable("id") Long id) {
		Optional<Admin> optAdmin = adminRepository.findById(id);
		if(optAdmin.isPresent()) {
			return optAdmin.get();
		}
		return null;
	}
	
	@GetMapping
	public Iterable<Admin> getAllAdmin(){
		return adminRepository.findAll();
	}
	
	@GetMapping("/search/{keyword}")
	public List<Admin> searchAdmin(@PathVariable("keyword") String keyword){
		
		List<Admin> adminList = new ArrayList<>();
		
		adminList.addAll(adminRepository.getAdminByNameContains(keyword));
		
		List<Admin> allAdmins= (List<Admin>) adminRepository.findAll();
		for (Admin admin : allAdmins) {
			if((""+admin.getId()).contains(keyword)) {
				adminList.add(admin);
			}
		}
		
		return adminList;
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteAdmin(@PathVariable("id") Long id) {
		try {
			adminRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
