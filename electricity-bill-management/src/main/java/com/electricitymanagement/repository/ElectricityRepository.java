package com.electricitymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.electricitymanagement.model.Electricity;

public interface ElectricityRepository extends CrudRepository<Electricity, Long>{

}
