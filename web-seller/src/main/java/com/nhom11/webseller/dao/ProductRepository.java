package com.nhom11.webseller.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom11.webseller.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
