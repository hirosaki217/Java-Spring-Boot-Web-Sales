package com.nhom11.webseller.dto;

import java.io.Serializable;
import java.util.List;

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
	private long id;
	@NotEmpty(message = "tên không được để trống")
	@Size(min = 4, message = "dài hơn hoặc bằng 4 ký tự")
	private String name;
	
	
	private int length;
	private int width;
	private int height;
	private int maximumLoad;
	private int minimumMaximumSpeed;
	private int maximumMaximumSpeed;
	private int battery;
	private int weight;
	private int distanceMin;
	private int distanceMax;
	

	private long manufacturerId;

	private long catergoryId;
	private List<ProductOptionRequest> optionRequests;
	private boolean isEdit;
	public boolean getIsEdit() {
		return isEdit;
	}
}
