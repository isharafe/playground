package com.my.product.catalog;

import com.my.product.catalog.repository.ProductPriceProjection;
import com.my.product.client.dto.InventoryDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private String productId;
	private String productName;
	private String productDescription;
	private Double productQuantity = 0.0;
	private Double productPrice;
	private String productImage;

	public ProductDto() {
		//
	}

	public ProductDto(ProductPriceProjection product, InventoryDto inv) {
		if (product != null) {
			this.categoryId = product.getCategoryId();
			this.categoryName = product.getCategoryName();
			this.categoryDescription = product.getCategoryDescription();

			this.productId = product.getProductId();
			this.productName = product.getProductName();
			this.productDescription = product.getProductDescription();
			this.productImage = product.getProductImage();
			this.productPrice = product.getProductPrice();
		}

		if (inv != null) {
			this.productQuantity = inv.getQuantity();
		}
	}
}
