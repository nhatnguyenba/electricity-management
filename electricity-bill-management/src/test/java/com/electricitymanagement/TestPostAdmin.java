package com.electricitymanagement;

import static org.junit.jupiter.api.Assertions.assertSame;

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
public class TestPostAdmin {
	
	AdminController adminController;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Test
	public void testCase1() {
		adminController = new AdminController(adminRepository);
		Admin admin = new Admin();
		admin.setName("Nguyen Ba Nhat");
		admin.setAddress("Trieu Son, Thanh Hoa");
		admin.setPhone("0337393811");
		admin.setEmail("nhatnguyenmd568@gmail.com");
		admin.setIdNumber("112233445566");
		admin.setUsername("nhat");
		admin.setPassword("minhdan");
		Admin receivedAdmin = adminController.postAdmin(admin);
		
		assertSame(admin, receivedAdmin);
	}
	
	@Test
	public void testCase2() {
		adminController = new AdminController(adminRepository);
		Admin admin = new Admin();
		Admin receivedAdmin = adminController.postAdmin(admin);
		
		assertSame(admin, receivedAdmin);
	}
	
	
	@Test
	public void testCase3() {
		adminController = new AdminController(adminRepository);
		Admin admin = new Admin();
		admin.setName("Nguyen Ba Nhat");
		admin.setAddress("Trieu Son, Thanh Hoa");
		admin.setPhone("0337393811");
		Admin receivedAdmin = adminController.postAdmin(admin);
		
		assertSame(admin, receivedAdmin);
	}
	
	@Test
	public void testCase4() {
		adminController = new AdminController(adminRepository);
		Admin admin = null;
		Admin receivedAdmin = adminController.postAdmin(admin);
		
		assertSame(admin, receivedAdmin);
	}
	
	
}
