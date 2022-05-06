package com.nhom11.webseller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom11.webseller.dao.ProductOptionRepository;
import com.nhom11.webseller.service.ProductOptionService;

@Service
@Transactional
public class ProductOptionServiceImpl implements ProductOptionService{
	@Autowired
	private ProductOptionRepository optionRepository;

	@Override
	public void flush() {
		optionRepository.flush();
	}

	@Override
	
	public void updateProductId(long id, long oId) {
		optionRepository.updateProductId(id, oId);
	}
	
	
}
