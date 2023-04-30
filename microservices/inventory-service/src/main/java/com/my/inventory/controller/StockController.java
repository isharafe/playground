package com.my.inventory.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.inventory.model.StockItem;
import com.my.inventory.model.StockItemDto;
import com.my.inventory.service.StockService;

@RestController
@RequestMapping(path = "/api/inventory")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockItemDto> getItems(@RequestBody Set<String> ids) {
		List<StockItem> items = stockService.getItems(ids);
		return items.stream().map(StockItemDto::new).toList();
	}
}
