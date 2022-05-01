package com.nhom11.webseller.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String name;
	private int length;
	private int width;
	private int height;
	private int maximum_load;
	private int minimum_maximum_speed;
	private int maximum_maximum_speed;
	private int batery;
	private int weight;
	private int minimum_charging_time;
	private int maximum_charging_time;
	private int distance_min;
	private int distance_max;
	private float price;
	
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String image;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Manufacturer manufacturer;
	
	
	@OneToMany(mappedBy = "product", cascade =CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<ProductTag> productTags;
	
	@OneToMany(mappedBy = "product", cascade =CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Color> colors;
	
	@OneToMany(mappedBy = "product", cascade =CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "product", cascade =CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<CartItem> cartItems;
}
