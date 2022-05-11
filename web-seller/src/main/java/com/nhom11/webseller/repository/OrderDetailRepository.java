package com.nhom11.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom11.webseller.model.OrderDetail;
import com.nhom11.webseller.model.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK>{

}
