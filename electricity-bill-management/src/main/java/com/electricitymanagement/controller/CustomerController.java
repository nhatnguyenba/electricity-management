package com.electricitymanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.electricitymanagement.model.Customer;
import com.electricitymanagement.repository.CustomerRepository;

@RestController
@RequestMapping(path="/customer", produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerController {
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		}
		return null;
	}
	
	@GetMapping
	public Iterable<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/search/{keyword}")
	public List<Customer> searchCustomer(@PathVariable("keyword") String keyword){
		
		List<Customer> customerList = new ArrayList<>();
		
		customerList.addAll(customerRepository.getCustomerByNameContains(keyword));
		
		Iterable<Customer> allCustomers= customerRepository.findAll();
		for (Customer customer : allCustomers) {
			if((""+customer.getId()).contains(keyword)) {
				customerList.add(customer);
			}
		}
		
		return customerList;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
	}
	
	@GetMapping("/debtor")
	public List<Customer> getDebtor(){
		return customerRepository.getDebtor();
	}
	
	@GetMapping("/notDebtor")
	public List<Customer> getnotDebtor(){
		return customerRepository.getNotDebtor();
	}
	
	@GetMapping("/100MostConsumer")
	public List<Customer> getTop100MostConsumer(){
		return customerRepository.getTop100MostConsumer();
	}
	
	@GetMapping("/100LeastConsumer")
	public List<Customer> getTop100LeastConsumer(){
		return customerRepository.getTop100LeastConsumer();
	}
}
