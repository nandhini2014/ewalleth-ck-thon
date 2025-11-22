package com.ewallet.order.service.payment;

import com.ewallet.order.dto.OrderRequestDTO;
import com.ewallet.order.dto.ProductDTO;
import com.ewallet.order.entity.Product;
import com.ewallet.order.entity.Wallet;

public interface PaymentService {
    void processOrder(OrderRequestDTO orderRequest, ProductDTO product, Wallet wallet, Double totalCost);
}
