package com.electricitymanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.electricitymanagement.model.Customer;

@Controller
@RequestMapping("/customerList")
public class CustomerListController {

	private RestTemplate rest = new RestTemplate();

	@GetMapping
	public String showFollowingCustomerList(Model model) {
		model.addAttribute("customers", getCustomerIsDebtor());
		return "customer_templates/followCustomerList";
	}

	@GetMapping("/showList")
	public String showListChoosen(@RequestParam("listType") String listType, Model model) {
		if (listType.equals("1")) {
			model.addAttribute("listType", "1");
			model.addAttribute("customers", getCustomerIsDebtor());
		} else if (listType.equals("2")) {
			model.addAttribute("listType", "2");
			model.addAttribute("customers", getCustomerIsNotDebtor());
		}else if(listType.equals("3")) {
			model.addAttribute("listType", "3");
			model.addAttribute("customers", getTop100MostConsumer());
		}
		else if(listType.equals("4")) {
			model.addAttribute("listType", "4");
			model.addAttribute("customers", getTop100LeastConsumer());
		}
		return "customer_templates/followCustomerList";
	}

	public List<Customer> getCustomerIsDebtor() {
		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer/debtor", Customer[].class);
		return Arrays.asList(customerArr);
	}

	public List<Customer> getCustomerIsNotDebtor() {
		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer/notDebtor", Customer[].class);
		return Arrays.asList(customerArr);
	}

	public List<Customer> getTop100MostConsumer() {
		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer/100MostConsumer", Customer[].class);
		return Arrays.asList(customerArr);
	}

	public List<Customer> getTop100LeastConsumer() {
		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer/100LeastConsumer", Customer[].class);
		return Arrays.asList(customerArr);
	}

}
