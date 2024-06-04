package com.example.myEShop.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;

    public OrderController(OrderService orderService, OrderDTOMapper orderDTOMapper){
        this.orderService = orderService;
        this.orderDTOMapper = orderDTOMapper;
    }

    @GetMapping("/getOrders")
    public List<OrderDTO> getOrders(){
        return orderService.getOrders()
                .stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }
    @PostMapping("/createOrder")
    public void handlingCreationOfOrder(){
        orderService.createOrder();
    }
}
