package com.ewallet.order.controller;

import com.ewallet.order.dto.OrderRequestDTO;
import com.ewallet.order.dto.OrderResponseDTO;
import com.ewallet.order.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> processOrder(OrderRequestDTO orderRequest){
        OrderResponseDTO orderResponseDTO = orderService.processOrder(orderRequest);
        return ResponseEntity.ok(orderResponseDTO);

    }

}
