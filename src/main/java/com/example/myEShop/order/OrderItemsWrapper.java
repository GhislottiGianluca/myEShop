package com.example.myEShop.order;

import com.example.myEShop.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderItemsWrapper {

    private List<ProductDTO> products;
    private List<Integer> quantities;
    private LocalDateTime createdAt;
    private OrderStatus status;

    public OrderItemsWrapper(List<ProductDTO> products, List<Integer> quantities, LocalDateTime createdAt, OrderStatus status){
        this.products = products;
        this.quantities = quantities;
        this.createdAt = createdAt;
        this.status = status;
    }
}
