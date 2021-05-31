package com.electricitymanagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.electricitymanagement.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	public List<Customer> getCustomerByNameContains(String keyword);
	
	
	@Modifying
	@Transactional
	@Query(value = "update customer set so_dien = ?1\r\n"
			+ "where id = ?2", nativeQuery = true)
	public void updateElectricityAmount(int electricityAmount, Long id);
	
	@Query(value = "select * from customer where debt > 0", nativeQuery = true)
	public List<Customer> getDebtor();
	
	@Query(value = "select * from customer where debt = 0", nativeQuery = true)
	public List<Customer> getNotDebtor();
	
	@Query(value = "select * from customer order by so_dien desc limit 100", 
			nativeQuery = true)
	public List<Customer> getTop100MostConsumer();
	
	@Query(value = "select * from customer order by so_dien limit 100", 
			nativeQuery = true)
	public List<Customer> getTop100LeastConsumer();
}
