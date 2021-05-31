package com.electricitymanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electricitymanagement.model.Customer;
import com.electricitymanagement.model.Electricity;
import com.electricitymanagement.repository.CustomerRepository;
import com.electricitymanagement.repository.ElectricityRepository;

@RestController
@RequestMapping(path = "/electricity", produces = "application/json")
@CrossOrigin(origins = "*")
public class ElectricityController {
	@Autowired
	private CustomerRepository customerRepository;

	private ElectricityRepository electricityRepo;
	
	@Autowired
	public ElectricityController(ElectricityRepository electricityRepository) {
		this.electricityRepo = electricityRepository;
	}

	@PostMapping("/electricityAmount")
	public boolean updateElectricityAmount(@RequestBody Customer customer) {
		System.out.println("Vao server");
		System.out.println("So dien server: " + customer.getSoDien());
		System.out.println("id server: " + customer.getId());
		try {
			customerRepository.updateElectricityAmount(customer.getSoDien(), customer.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@PostMapping("/updatePriceAndVAT")
	public boolean updatePriceAndVAT(@RequestBody Electricity electricity) {
		try {
			electricityRepo.save(electricity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	@GetMapping("/{id}")
	public Electricity getElectricityById(@PathVariable("id") Long id) {
		try {
			return electricityRepo.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
