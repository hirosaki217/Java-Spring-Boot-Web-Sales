package com.nhom11.webseller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color {
	@Id
	@GeneratedValue
	private long id;
	@Column(columnDefinition = "nvarchar(50)", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn( name =  "product_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Product product;
	
	
}
