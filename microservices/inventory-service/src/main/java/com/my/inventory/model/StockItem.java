package com.my.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock_item")
@Getter
@Setter
public class StockItem extends BaseModel {

	@Column(name = "product_id")
	private String productId;
	@Column(name = "quantity")
	private Double quantity;

	@ManyToOne
	private Stock stock;

}
