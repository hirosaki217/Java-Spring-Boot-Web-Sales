package com.nhom11.webseller.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable{

	@NotEmpty
	@Size(min = 4)
	private String name;
	
	
	private int length;
	private int width;
	private int height;
	private int maximumLoad;
	private int minimumMaximumSpeed;
	private int maximumMaximumSpeed;
	private int battery;
	private int weight;
	private int minimumChargingTime;
	private int maximumChargingTime;
	private int distanceMin;
	private int distanceMax;
	private float price;
	private String image;
	private MultipartFile imageFile;
	private int quantity;
	private long manufacturerId;
	
}
