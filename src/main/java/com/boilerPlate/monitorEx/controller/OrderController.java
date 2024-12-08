package com.boilerPlate.monitorEx.controller;

import com.boilerPlate.monitorEx.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {
    public final OrderService orderService;

    @GetMapping("v1/order")
    public String order() {
        log.info("order");
        orderService.order();
        return "order";
    }

    @GetMapping("v1/cancel")
    public String cancel() {
        log.info("cancel");
        orderService.cancel();
        return "cancel";
    }

    @GetMapping("v1/stock")
    public int stock() {
        log.info("stock");
        return orderService.getStock().get();
    }
}
