package com.electricitymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.electricitymanagement.model.Customer;
import com.electricitymanagement.model.Electricity;

@Controller
@RequestMapping("/electricity")
public class ElectricityController {
	private RestTemplate rest = new RestTemplate();

	@GetMapping
	public String showElectricityManagement(Model model) {
		try {
		Electricity electricity = rest.getForObject("http://localhost:8069/electricity/{id}", 
				Electricity.class, 1);
		model.addAttribute("electricity", electricity);
		}
		catch (Exception e) {
			model.addAttribute("electricity", new Electricity());
		}
		return "electricity/electricityManagement";
	}
	
	@GetMapping("/updateElectricityAmount")
	public String updateElectricityAmount(@RequestParam("electricityAmount") 
	int electricityAmount, @RequestParam("id") Long id, Model model) {
		System.out.println("id: "+id);
		System.out.println("soDien: "+electricityAmount);
		Customer customer = 
				rest.getForObject("http://localhost:8069/customer/{id}", Customer.class, id);
		customer.setSoDien(electricityAmount);
		rest.postForObject("http://localhost:8069/electricity/electricityAmount", customer, Void.class);
		model.addAttribute("customer", customer);
		model.addAttribute("message", "Cập nhật số điện thành công");
		return "customer_templates/viewCustomerDetail";
	}
	
	@GetMapping("/updatePriceAndVAT")
	public String updatePriceAndVAT(Electricity electricity, Model model) {
		
		rest.postForObject("http://localhost:8069/electricity/updatePriceAndVAT", 
				electricity, Void.class);
		model.addAttribute("message", "Cập nhật thành công!");
		return "electricity/electricityManagement";
		
	}
}
