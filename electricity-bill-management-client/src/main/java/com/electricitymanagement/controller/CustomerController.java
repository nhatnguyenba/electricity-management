package com.electricitymanagement.controller;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.electricitymanagement.model.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private static RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public String showCustomerManagement(Model model) {
//		loadAllCustomerToModel(model);
		return "/customer_templates/customerManagement";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "/customer_templates/addCustomerForm";
	}
	
	@GetMapping("/editAndRemove")
	public String showEditAndRemove(Model model) {
		loadAllCustomerToModel(model);
		return "/customer_templates/editAndRemoveCustomer";
	}

	@PostMapping
	public String addCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Co loi");
			return "/customer_templates/addCustomerForm";
		}
		System.out.println("Khong co loi");
		rest.postForObject("http://localhost:8069/customer", customer, Customer.class);
		return "/customer_templates/addCustomerSuccessful";
	}
	

	@PostMapping("/save")
	public String saveCustomer(@Valid Customer customer, Errors errors, Model model) {
		if(errors.hasErrors()) {
			return "/customer_templates/editCustomerForm";
		}
		rest.postForObject("http://localhost:8069/customer", customer, Customer.class);
		return "/customer_templates/editCustomerSuccessful";
	}

	@GetMapping("/edit/{id}")
	public String editCustomer(@PathVariable("id") Long id, Model model) {
		Customer customer = rest.getForObject("http://localhost:8069/customer/{id}", Customer.class, id);
		model.addAttribute("customer", customer);
		return "/customer_templates/editCustomerForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id, Model model) {
		try {
		rest.delete("http://localhost:8069/customer/{id}", id);
		loadAllCustomerToModel(model);
		}catch(Exception e) {
			model.addAttribute("message", "Không thể xóa khách hàng này do nó đang được sử dụng.");
			loadAllCustomerToModel(model);
		}
		return "/customer_templates/deleteCustomerSuccessful";
	}
	
	@GetMapping("/detail/{id}")
	public String viewCustomerDetail(@PathVariable("id") Long id, Model model) {
		
		Customer customer = 
				rest.getForObject("http://localhost:8069/customer/{id}", Customer.class, id);
		model.addAttribute("customer", customer);
		return "customer_templates/viewCustomerDetail";
	}

	@GetMapping("/search")
	public String findCustomer(@RequestParam("keyword") String keyword, Model model) {
		if (keyword.equals("")) {
			return showEditAndRemove(model);
		}

		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer/search/{keyword}", Customer[].class,
				keyword);
		List<Customer> customers = Arrays.asList(customerArr);
		model.addAttribute("customers", customers);
		return "/customer_templates/editAndRemoveCustomer";
	}

	public static void loadAllCustomerToModel(Model model) {
		Customer[] customerArr = 
				rest.getForObject("http://localhost:8069/customer", Customer[].class);
		List<Customer> customers = Arrays.asList(customerArr);
		model.addAttribute("customers", customers);
	}
}
