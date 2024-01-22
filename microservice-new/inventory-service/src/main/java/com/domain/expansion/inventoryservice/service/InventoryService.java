package com.domain.expansion.inventoryservice.service;

import com.domain.expansion.inventoryservice.dto.InventoryAllResponse;
import com.domain.expansion.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);

    public List<InventoryAllResponse> getInventories();
}
