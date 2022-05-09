package com.nhom11.webseller.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom11.webseller.dao.ProductOptionRepository;
import com.nhom11.webseller.model.ProductOption;
import com.nhom11.webseller.service.ProductOptionService;
import com.nhom11.webseller.service.StorageService;

@Service
@Transactional
public class ProductOptionServiceImpl implements ProductOptionService{
	@Autowired
	private ProductOptionRepository optionRepository;

	@Autowired
	private StorageService storageService;
	
	@Override
	public int deleteProductOptionByProductID(long pId) {

		return optionRepository.deleteProductOptionByProductID(pId);
	}
	
	
	
	@Override
	public List<ProductOption> findProductOptionsByProductId(long pId) {
		return optionRepository.findProductOptionsByProductId(pId);
	}



	@Override
	public void flush() {
		optionRepository.flush();
	}

	@Override
	
	public void updateProductId(long id, long oId) {
		optionRepository.updateProductId(id, oId);
	}

	@Override
	public <S extends ProductOption> S save(S entity) {
		return optionRepository.save(entity);
	}
	
	
}
