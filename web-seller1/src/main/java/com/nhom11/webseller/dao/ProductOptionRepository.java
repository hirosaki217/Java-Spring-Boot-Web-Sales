package com.nhom11.webseller.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.model.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>{
	@Modifying
	@Query(value = "UPDATE product_options set product_id=:pId WHERE id=:oId", nativeQuery = true)
	public int updateProductId(@Param("pId") long id,@Param("oId") long oId);
	
	@Modifying
	@Query(value = "delete from product_options where product_id=:pId", nativeQuery = true)
	public int deleteProductOptionByProductID(@Param("pId") long pId);
	
	@Query(value = "select * from product_options where product_id=:pId", nativeQuery = true)
	public List<ProductOption> findProductOptionsByProductId(@Param("pId") long pId);
}
