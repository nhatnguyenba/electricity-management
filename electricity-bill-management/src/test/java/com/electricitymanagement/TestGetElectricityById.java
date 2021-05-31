package com.electricitymanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.electricitymanagement.controller.ElectricityController;
import com.electricitymanagement.model.Electricity;
import com.electricitymanagement.repository.ElectricityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGetElectricityById {

	ElectricityController electricityController;

	@Autowired
	ElectricityRepository electricityRepository;

	// id co trong database
	@Test
	public void tesCase1() {
		electricityController = new ElectricityController(electricityRepository);
		Electricity electricity = electricityController.getElectricityById(1L);

		assertNotNull(electricity);
	}

	// id co trong database
	@Test
	public void tesCase2() {
		electricityController = new ElectricityController(electricityRepository);
		Electricity electricity = electricityController.getElectricityById(100L);

		assertNull(electricity);
	}

}
