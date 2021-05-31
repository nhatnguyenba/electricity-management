package com.electricitymanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.electricitymanagement.controller.CustomerController;
import com.electricitymanagement.controller.ElectricityController;
import com.electricitymanagement.model.Customer;
import com.electricitymanagement.repository.CustomerRepository;
import com.electricitymanagement.repository.ElectricityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUpdateElectricityAmount {

	ElectricityController electricityController;
	CustomerController customerController;
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ElectricityRepository electricityRepository;
	
	//customer co trong database
	@Test
	public void tesCase1() {
		electricityController = new ElectricityController(electricityRepository);
		customerController = new CustomerController(customerRepository);
		Customer customer = customerController.getCustomerById(5L);
		customer.setSoDien(200);
		boolean isTrue = electricityController.updateElectricityAmount(customer);
	
		assertTrue(isTrue);
	}
	
	//customer khong co trong database
	@Test
	public void tesCase2() {
		electricityController = new ElectricityController(electricityRepository);
		Customer customer = new Customer();
		customer.setId(10000L);
		customer.setSoDien(200);
		boolean isTrue = electricityController.updateElectricityAmount(customer);
	
		assertFalse(isTrue);
	}
	
	//customer voi so dien am
	@Test
	public void tesCase3() {
		electricityController = new ElectricityController(electricityRepository);
		customerController = new CustomerController(customerRepository);
		Customer customer = customerController.getCustomerById(5L);
		customer.setSoDien(-200);
		boolean isTrue = electricityController.updateElectricityAmount(customer);
	
		assertFalse(isTrue);
	}
	
	//customer voi so dien = 0
	@Test
	public void tesCase4() {
		electricityController = new ElectricityController(electricityRepository);
		customerController = new CustomerController(customerRepository);
		Customer customer = customerController.getCustomerById(5L);
		customer.setSoDien(0);
		boolean isTrue = electricityController.updateElectricityAmount(customer);
	
		assertTrue(isTrue);
	}
	

}
