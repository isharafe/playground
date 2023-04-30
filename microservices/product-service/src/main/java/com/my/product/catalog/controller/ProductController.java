package com.my.product.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.product.catalog.ProductCategory;
import com.my.product.catalog.ProductDto;
import com.my.product.catalog.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public Page<ProductDto> products(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String name,
			Pageable page) {
		return productService.getProducts(category, name, page);
	}

	@GetMapping("/categories")
	public List<ProductCategory> categories() {
		return productService.getCategories();
	}

}
