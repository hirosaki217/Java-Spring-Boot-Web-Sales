package com.nhom11.webseller.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.nhom11.webseller.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByNameContaining(String name);
    Page<Product> findByNameContaining(String name, Pageable pageable);
    @Query("SELECT p FROM Product p")
    Page<Product> findProducts(Pageable pageable);
    
    Page<Product> findByCatergoryId(long catergoryId, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE name like '%'+:name+'%'",nativeQuery = true)
	Page<Product> findAllByName(@Param(value = "name") String name, Pageable pageable);
}
