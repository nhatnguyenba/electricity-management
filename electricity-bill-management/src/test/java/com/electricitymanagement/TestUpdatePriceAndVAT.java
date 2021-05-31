package com.electricitymanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class TestUpdatePriceAndVAT {

	ElectricityController electricityController;

	@Autowired
	ElectricityRepository electricityRepository;

	// dau vao hop le
	@Test
	public void tesCase1() {
		electricityController = new ElectricityController(electricityRepository);
		Electricity electricity = electricityController.getElectricityById(1L);
		electricity.setPrice(16000);
		electricity.setVat(0.25f);
		boolean isTrue = electricityController.updatePriceAndVAT(electricity);

		assertTrue(isTrue);
	}

	// gia khong hop le
	@Test
	public void tesCase2() {
		electricityController = new ElectricityController(electricityRepository);
		Electricity electricity = electricityController.getElectricityById(1L);
		electricity.setPrice(-16000);
		electricity.setVat(0.25f);
		boolean isTrue = electricityController.updatePriceAndVAT(electricity);

		assertFalse(isTrue);
	}

	// thue vat khong dung dinh dang
	@Test
	public void tesCase3() {
		electricityController = new ElectricityController(electricityRepository);
		Electricity electricity = electricityController.getElectricityById(1L);
		electricity.setPrice(16000);
		electricity.setVat(0.25999999f);
		boolean isTrue = electricityController.updatePriceAndVAT(electricity);

		assertFalse(isTrue);
	}

}
