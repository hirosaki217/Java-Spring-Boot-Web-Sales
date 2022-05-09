package com.nhom11.webseller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom11.webseller.model.Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{ 
	@Query(value = "select * from manufacturers where name like '%'+:name+'%'" , nativeQuery = true)
	List<Manufacturer> findByNameContaining(@Param(value = "name") String name);
}
