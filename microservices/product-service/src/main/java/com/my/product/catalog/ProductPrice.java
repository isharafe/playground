package com.my.product.catalog;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(indexes = {
		@Index(unique = true, columnList ="effective_date DESC, product_eid")})
@Getter
@Setter
public class ProductPrice extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "product_eid")
	private Product product;
	private BigDecimal price;
	@Column(name = "effective_date")
	private Date effectiveDate;

}
