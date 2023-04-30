package com.my.product.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product", indexes = @Index(columnList = "product_id"))
public class Product extends BaseModel {

	@Column(name = "product_id", length = 10)
	private String productId;
	private String name;
	private String description;
	private String image;

	@ManyToOne
	private ProductCategory category;

}
