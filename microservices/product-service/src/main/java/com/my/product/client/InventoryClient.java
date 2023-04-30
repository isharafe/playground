package com.my.product.client;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.my.product.client.dto.InventoryDto;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

	@PostMapping(value = "/api/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    List<InventoryDto> getProductInventory(@RequestBody Set<String> productIds);

}
