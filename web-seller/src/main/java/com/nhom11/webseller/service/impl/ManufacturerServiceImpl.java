package com.nhom11.webseller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.repository.ManufacturerRepository;
import com.nhom11.webseller.repository.ProductRepository;
import com.nhom11.webseller.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	private ManufacturerRepository repository;

	
	
	@Override
	public List<Manufacturer> findByNameContaining(String name) {
		name = name.trim();
		return repository.findByNameContaining(name);
	}

	@Override
	public List<Manufacturer> findAll() {
		return repository.findAll();
	}

	@Override
	public <S extends Manufacturer> S save(S entity) {
		return repository.save(entity);
	}

	@Override
	public Optional<Manufacturer> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public <S extends Manufacturer> boolean exists(Example<S> example) {
		return repository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Manufacturer entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Manufacturer getById(Long id) {
		return repository.getById(id);
	}
	
	

	
}
