package com.domain.expansion.inventoryservice.controller;

import com.domain.expansion.inventoryservice.dto.InventoryResponse;
import com.domain.expansion.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //old controller only get a single skuCode
//    @GetMapping("/{skuCode}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("skuCode") String skuCode){
//        return inventoryService.isInStock(skuCode);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return  inventoryService.isInStock(skuCode);
    }
}
