package com.electricitymanagement;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.electricitymanagement.controller.AdminController;
import com.electricitymanagement.model.Admin;
import com.electricitymanagement.repository.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGetAllAdmin {

	AdminController adminController;
	@Autowired
	AdminRepository adminRepository;
	
	@Test
	public void tesCase1() {
		adminController = new AdminController(adminRepository);
		Iterable<Admin> admins = adminController.getAllAdmin();
		
		assertNotNull(admins);
	}
}
