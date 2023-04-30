package com.my.product.catalog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.my.product.catalog.service.ProductService;

@SpringBootTest
/**
 * works with data in "/src/test/resources/data.sql" file
 */
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	void testProductServiceCrud() {

		Product p1 = productService.findProduct(1L).orElse(null);
		assertThat(p1).isNotNull();
		assertThat(p1.getName()).isEqualTo("product name 1");

		ProductCategory pc1 = p1.getCategory();
		assertThat(pc1).isNotNull();
		assertThat(pc1.getCategoryId()).isEqualTo("cat-001");
	}

	@Test
	void testSearchWithCategoryId() {
		Pageable page = Pageable.ofSize(3);
		Page<ProductDto> products = productService.getProducts("cat-002", "", page);
		products.forEach(p -> {
			assertThat(p.getCategoryId()).isEqualTo("cat-002");
		});

		assertThat(products.getTotalPages()).isEqualTo(2);
		assertThat(products.getContent()).hasSize(3);
	}

	@Test
	void testSearchWithCategoryIdAndName() {
		Pageable page = Pageable.ofSize(3);
		Page<ProductDto> products = productService.getProducts("cat-002", "d", page);
		products.forEach(p -> {
			assertThat(p.getCategoryId()).isEqualTo("cat-002");
		});

		assertThat(products.getTotalPages()).isEqualTo(1);
		assertThat(products.getContent()).hasSize(1);

		ProductDto product = products.getContent().get(0);
		assertThat(product.getProductName()).isEqualTo("diff name 10");
	}

	@Test
	void testSearchWithoutCategoryIdAndName() {
		Pageable page = Pageable.ofSize(3);
		Page<ProductDto> products = productService.getProducts("", "d", page);
		products.forEach(p -> {
			assertThat(p.getCategoryId()).isEqualTo("cat-002");
		});

		assertThat(products.getTotalPages()).isEqualTo(1);
		assertThat(products.getContent()).hasSize(1);

		ProductDto product = products.getContent().get(0);
		assertThat(product.getProductName()).isEqualTo("diff name 10");
	}

}
