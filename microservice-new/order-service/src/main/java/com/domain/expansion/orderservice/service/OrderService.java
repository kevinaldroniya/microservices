package com.domain.expansion.orderservice.service;

import com.domain.expansion.orderservice.dto.OrderRequest;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest);
}
