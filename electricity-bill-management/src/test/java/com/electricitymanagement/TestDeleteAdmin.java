package com.electricitymanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.electricitymanagement.controller.AdminController;
import com.electricitymanagement.repository.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDeleteAdmin {
	
	AdminController adminController;
	@Autowired
	AdminRepository adminRepository;
	
	//mot id co trong database
	@Test
	public void tesCase1() {
		adminController = new AdminController(adminRepository);
		Long id = 9L;
		boolean isTrue = adminController.deleteAdmin(id);
		
		assertTrue(isTrue);
	}
	
	//mot id khong co trong database
	@Test
	public void tesCase2() {
		adminController = new AdminController(adminRepository);
		Long id = 100L;
		boolean isTrue = adminController.deleteAdmin(id);
		
		assertFalse(isTrue);
	}
}
