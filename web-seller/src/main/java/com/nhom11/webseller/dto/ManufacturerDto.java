package com.nhom11.webseller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDto implements Serializable {
	private long id;
	@NotEmpty
	@Size(min = 4)
	private String name;
	private String country;
	
	private boolean isEdit;
	
}
