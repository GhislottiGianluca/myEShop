package com.example.myEShop.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){this.orderService = orderService;}

    @GetMapping("/getOrders")
    public List<OrderItemsWrapper> getOrders(){return orderService.getOrders();}

    @PostMapping("/createOrder")
    public void handlingCreationOfOrder(){
        orderService.createOrder();
    }
}
