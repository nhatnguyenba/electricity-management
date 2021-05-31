package com.electricitymanagement;

import static org.junit.Assert.assertTrue;

import java.util.List;

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
public class TestSearchAdmin {
	
	AdminController adminController;
	@Autowired
	AdminRepository adminRepository;
	
	//dau vao trung voi id cua admin
	@Test
	public void tesCase1() {
		adminController = new AdminController(adminRepository);
		List<Admin> admins = adminController.searchAdmin("5");
		
		assertTrue(admins.size() > 0);
	}
	
	//dau vao trung voi tên cua admin
	@Test
	public void tesCase2() {
		adminController = new AdminController(adminRepository);
		List<Admin> admins = adminController.searchAdmin("nhat");
		
		assertTrue(admins.size() > 0);
	}
	
	//dau vao khong trung voi id va ten cua admin
	@Test
	public void tesCase3() {
		adminController = new AdminController(adminRepository);
		List<Admin> admins = adminController.searchAdmin("***********");
		
		assertTrue(admins.size() == 0);
	}
}
