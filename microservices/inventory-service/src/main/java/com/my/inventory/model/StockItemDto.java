package com.my.inventory.model;

import lombok.Getter;

@Getter
public class StockItemDto {

	private String productId;
	private Double quantity;

	public StockItemDto(StockItem stockItem) {
		this.productId = stockItem.getProductId();
		this.quantity = stockItem.getQuantity();
	}
}
