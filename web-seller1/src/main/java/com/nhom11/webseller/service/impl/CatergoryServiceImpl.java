package com.nhom11.webseller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom11.webseller.dao.CatergoryRepository;
import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.service.CatergoryService;

@Service
@Transactional
public class CatergoryServiceImpl implements CatergoryService{
	@Autowired
	private CatergoryRepository catergoryRepository;

	@Override
	public List<Catergory> findAll() {
		return catergoryRepository.findAll();
	}
	
}
