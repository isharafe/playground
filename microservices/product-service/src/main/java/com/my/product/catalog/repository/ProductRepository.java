package com.my.product.catalog.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.my.product.catalog.Product;

import io.micrometer.core.annotation.Timed;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = """
SELECT
t5.category_id as categoryId, t5.name as categoryName, t5.description as categoryDescription,
t1.product_id as productId, t1.name as productName, t1.description as productDescription,
t1.image as productImage, t2.price as productPrice
FROM product t1
INNER JOIN (SELECT t3.product_eid, t3.effective_date, t3.price
    FROM product_price t3
    INNER JOIN
        (SELECT product_eid, MAX(effective_date) AS effective_date
        FROM product_price
        WHERE effective_date <= :pdate
        GROUP BY product_eid) t4
    ON t3.product_eid=t4.product_eid AND t3.effective_date=t4.effective_date
) t2 ON t1.eid=t2.product_eid
INNER JOIN product_category t5 ON t1.category_eid=t5.eid
WHERE (:name is null OR :name='' OR t1.name LIKE %:name%) AND (:categoryId is null OR :categoryId='' OR t5.category_id=:categoryId)
""",
		nativeQuery = true)
	@Timed(value = "database.query.time", description = "product service: jpa repository: findByNameAndCategory")
	public Page<ProductPriceProjection> findByNameAndCategory(
			@Param("name") String name,
			@Param("pdate") Date effectiveDate,
			@Param("categoryId") String categoryId,
			Pageable page);
}
