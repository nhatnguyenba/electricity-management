package com.electricitymanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.electricitymanagement.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	public List<Admin> getAdminByNameContains(String keyword);
}
