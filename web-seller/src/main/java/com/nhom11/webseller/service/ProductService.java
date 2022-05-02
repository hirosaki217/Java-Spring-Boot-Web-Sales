package com.nhom11.webseller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nhom11.webseller.dao.ProductRepository;
import com.nhom11.webseller.model.Product;


public interface ProductService {

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

	void delete(Product entity);

	long count();

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Long id);

	Optional<Product> findById(Long id);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> S save(S entity);
	
	
}
