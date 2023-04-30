package com.my.inventory.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.my.inventory.model.StockItem;
import com.my.inventory.repository.StockItemRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

	private final StockItemRepository repository;

	@Transactional
	public List<StockItem> getItems(Set<String> ids) {
		return repository.findByProductIdIn(ids);
	}
}
