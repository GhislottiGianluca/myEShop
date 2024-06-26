package com.example.myEShop.order;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(Order order){
        return new OrderDTO(
                order.getItems(),
                order.getCreatedAt(),
                order.getStatus());
    }
}
