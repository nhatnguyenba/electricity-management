package com.electricitymanagement.controller;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.electricitymanagement.model.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public String showAdminManagement(Model model) {
//		loadAllCustomerToModel(model);
		return "/admin_templates/adminManagement";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "/admin_templates/addAdminForm";
	}
	
	@GetMapping("/editAndRemove")
	public String showEditAndRemove(Model model) {
		loadAllAdminToModel(model);
		return "/admin_templates/editAndRemoveAdmin";
	}

	@PostMapping
	public String addAdmin(@Valid Admin admin, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "admin_templates/addAdminForm";
		}
		rest.postForObject("http://localhost:8069/admin", admin, Admin.class);
//		loadAllCustomerToModel(model);
		return "/admin_templates/addAdminSuccessful";
	}
	

	@PostMapping("/save")
	public String saveAdmin(@Valid Admin admin, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "admin_templates/editAdminForm";
		}
		rest.postForObject("http://localhost:8069/admin", admin, Admin.class);
//		loadAllCustomerToModel(model);
		return "/admin_templates/editAdminSuccessful";
	}

	@GetMapping("/edit/{id}")
	public String editAdmin(@PathVariable("id") Long id, Model model) {
		Admin admin = rest.getForObject("http://localhost:8069/admin/{id}", Admin.class, id);
		model.addAttribute("admin", admin);
		return "/admin_templates/editAdminForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteAdmin(@PathVariable("id") Long id, Model model) {
		try {
		rest.delete("http://localhost:8069/admin/{id}", id);
		loadAllAdminToModel(model);
		}catch(Exception e) {
			model.addAttribute("message", "Không thể xóa admin này do nó đang được sử dụng.");
			loadAllAdminToModel(model);
		}
		return "/admin_templates/deleteAdminSuccessful";
	}

	@GetMapping("/search")
	public String findAdmin(@RequestParam("keyword") String keyword, Model model) {
		if (keyword.equals("")) {
			return showEditAndRemove(model);
		}

		Admin[] adminArr = rest.getForObject("http://localhost:8069/admin/search/{keyword}", Admin[].class,
				keyword);
		List<Admin> admins = Arrays.asList(adminArr);
		model.addAttribute("admins", admins);
		return "/admin_templates/editAndRemoveAdmin";
	}

	public void loadAllAdminToModel(Model model) {
		Admin[] adminArr = 
				rest.getForObject("http://localhost:8069/admin", Admin[].class);
		List<Admin> admins = Arrays.asList(adminArr);
		model.addAttribute("admins", admins);
	}
}
