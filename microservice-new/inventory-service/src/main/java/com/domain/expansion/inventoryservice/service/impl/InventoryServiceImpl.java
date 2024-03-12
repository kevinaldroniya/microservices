package com.domain.expansion.inventoryservice.service.impl;

import com.domain.expansion.inventoryservice.dto.InventoryResponse;
import com.domain.expansion.inventoryservice.repository.InventoryRepository;
import com.domain.expansion.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    //old only get a single skuCode
//    @Transactional(readOnly = true)
//    @Override
//    public boolean isInStock(String skuCode) {
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
//    }


    @Transactional(readOnly = true)
    @Override
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(
                        inventory -> InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                        ).toList();
    }
}
