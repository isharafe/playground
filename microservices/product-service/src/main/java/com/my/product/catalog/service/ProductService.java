package com.my.product.catalog.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.my.product.catalog.Product;
import com.my.product.catalog.ProductCategory;
import com.my.product.catalog.ProductDto;
import com.my.product.catalog.repository.ProductCategoryRepository;
import com.my.product.catalog.repository.ProductPriceProjection;
import com.my.product.catalog.repository.ProductRepository;
import com.my.product.client.InventoryClient;
import com.my.product.client.dto.InventoryDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

	private final ProductCategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final InventoryClient inventoryClient;

	@CircuitBreaker(name = "get-products-breaker", fallbackMethod = "getProductsFallBack")
	@Retry(name = "get-products-retry", fallbackMethod = "getProductsFallBack")
	public Page<ProductDto> getProducts(String categoryId, String name, Pageable pageable) {
		Date effectiveDate = new Date();
		Page<ProductPriceProjection> res = productRepository.findByNameAndCategory(name, effectiveDate, categoryId, pageable);
		if(! res.isEmpty()) {
			Map<String, ProductPriceProjection> productMap = res.getContent().stream()
					.collect(Collectors.toMap(ProductPriceProjection::getProductId, p -> p));
			List<InventoryDto> invs = inventoryClient.getProductInventory(productMap.keySet());
			Map<String, InventoryDto> productInventory = invs.stream()
					.collect(Collectors.toMap(InventoryDto::getProductId, inv -> inv));
			return res.map(p -> new ProductDto(productMap.get(p.getProductId()), productInventory.get(p.getProductId())));
		}

		return Page.empty(pageable);
	}

	public Page<ProductDto> getProductsFallBack(RuntimeException ex) {
		ProductDto noProduct = new ProductDto();
		noProduct.setCategoryName("No-Products");
		noProduct.setProductName(ex.getMessage());
		noProduct.setProductDescription("Please wait for a while and try again!");

		return new PageImpl<>(Arrays.asList(noProduct));
	}

	public Optional<Product> findProduct(Long productId) {
		return productRepository.findById(productId);
	}

	public Long createProduct(Product product) {
		productRepository.save(product);
		return product.getEid();
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	public List<ProductCategory> getCategories() {
		return categoryRepository.findAll();
	}

}
