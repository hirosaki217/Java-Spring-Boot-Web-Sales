package com.nhom11.webseller.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	@EqualsAndHashCode.Exclude
	private Order order;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	private Product product;
	
	private int quantity;
	private float price;
	
}
