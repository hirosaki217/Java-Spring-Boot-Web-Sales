package com.nhom11.webseller.dto;



import java.io.Serializable;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionRequest implements Serializable{
	private long id;
	private float price;
	private String image;
	private MultipartFile imageFile;
	private int quantity;
	
	private String color;
	private long productId;
	private String sku;
}
