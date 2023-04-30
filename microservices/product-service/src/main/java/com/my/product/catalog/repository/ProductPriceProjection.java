package com.my.product.catalog.repository;

public interface ProductPriceProjection {

	String getCategoryId();
	String getCategoryName();
	String getCategoryDescription();

	String getProductId();
	String getProductName();
	String getProductDescription();
	Double getProductPrice();
	String getProductImage();
}
