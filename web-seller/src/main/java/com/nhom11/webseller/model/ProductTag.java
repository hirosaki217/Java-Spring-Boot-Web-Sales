package com.nhom11.webseller.model;

import java.io.Serializable;

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
@Table(name = "product_tag")
@IdClass(ProductTagPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductTag implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "tag_id")
	@EqualsAndHashCode.Exclude
	private Tag tag;
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	private Product product;
}
