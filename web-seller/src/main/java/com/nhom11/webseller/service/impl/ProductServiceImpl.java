package com.nhom11.webseller.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom11.webseller.dao.ProductRepository;
import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.model.ProductOption;
import com.nhom11.webseller.service.ProductOptionService;
import com.nhom11.webseller.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductOptionService optionService;
	
	
	@Override
	public void flush() {
		productRepository.flush();
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void delete(Product entity) {
		optionService.deleteProductOptionByProductID(entity.getId());
		productRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepository.findAll(example, sort);
	}


	
}
