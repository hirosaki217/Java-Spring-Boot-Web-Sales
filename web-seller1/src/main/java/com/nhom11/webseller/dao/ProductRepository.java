package com.nhom11.webseller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom11.webseller.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
