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
public class TestGetAdminById {

	AdminController adminController;
	@Autowired
	AdminRepository adminRepository;
	
	//mot id co trong database
	@Test
	public void tesCase1() {
		adminController = new AdminController(adminRepository);
		Long id = 7L;
		Admin admin = adminController.getAdminById(id);
		
		assertNotNull(admin);
	}
	
	//mot id khong co trong database
	@Test
	public void tesCase2() {
		adminController = new AdminController(adminRepository);
		Long id = 100L;
		Admin admin = adminController.getAdminById(id);
		
		assertNotNull(admin);
	}
	

	
}
