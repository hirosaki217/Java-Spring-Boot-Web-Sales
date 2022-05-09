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
@Table(name = "cart_item")
@IdClass(CartItemPk.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	@Id
	@ManyToOne
	@JoinColumn(name = "cart_id")
	@EqualsAndHashCode.Exclude
	private Cart cart;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	private Product product;
	
	
	private int quantity;
	private float price;
}
