package com.my.inventory.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock extends BaseModel {

	private String name;
	private String description;

	@OneToMany(mappedBy = "stock")
	private List<StockItem> items;

}
