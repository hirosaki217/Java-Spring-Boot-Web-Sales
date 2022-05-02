package com.nhom11.webseller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom11.webseller.dao.ManufacturerRepository;
import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	private ManufacturerRepository repository;

	@Override
	public List<Manufacturer> findAll() {
		return repository.findAll();
	}
	
}
