package com.nhom11.webseller.service;

import java.util.List;

import com.nhom11.webseller.model.ProductOption;

public interface ProductOptionService {

	void updateProductId(long id, long oId);

	void flush();

	int deleteProductOptionByProductID(long oId);

	<S extends ProductOption> S save(S entity);

	List<ProductOption> findProductOptionsByProductId(long pId);

}
