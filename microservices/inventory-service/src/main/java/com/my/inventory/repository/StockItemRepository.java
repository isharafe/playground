package com.my.inventory.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.inventory.model.StockItem;

import io.micrometer.core.annotation.Timed;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
/*
	@Modifying
	@Query(value = "CREATE TEMPORARY TABLE temp_ids (product_id VARCHAR(10) )", nativeQuery = true)
	void createTempIdsTable();

	@Modifying
	@Query(value = "INSERT INTO temp_ids (product_id) VALUES (:id)", nativeQuery = true)
	void insertIntoTempIdsTable(@Param("id") String id);

	@Query(value = """
SELECT t1.eid, t1.version, t1.product_id, t1.quantity, t1.stock_eid
FROM stock_item t1
JOIN temp_ids t2 ON t1.product_id = t2.product_id
""",
			countQuery = "SELECT COUNT(*) FROM stock_item t1 JOIN temp_ids t2 ON t1.product_id = t2.id",
			nativeQuery = true)
	List<StockItem> findStockItems();
*/
	@Timed(value = "database.query.time", description = "inventory service: jpa repository: findByProductIdIn")
	List<StockItem> findByProductIdIn(Collection<String> productIds);
}
