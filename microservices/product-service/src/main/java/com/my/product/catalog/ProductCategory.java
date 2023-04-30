package com.my.product.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "category_id"))
@Getter
@Setter
public class ProductCategory extends BaseModel {

	@Column(name = "category_id")
	private String categoryId;
	private String name;
	private String description;

}
