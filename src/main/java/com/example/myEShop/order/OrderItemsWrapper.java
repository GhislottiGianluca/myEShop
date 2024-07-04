package com.example.myEShop.order;

import com.example.myEShop.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Wrapper class for order items.
 * <p>
 * This class encapsulates the details of the products in an order, including
 * their quantities, the creation time of the order, and the status of the order.
 * </p>
 */
@Getter
@Setter
public class OrderItemsWrapper {

    /**
     * The list of products in the order.
     */
    private List<ProductDTO> products;

    /**
     * The list of quantities for each product in the order.
     */
    private List<Integer> quantities;

    /**
     * The timestamp when the order was created.
     */
    private LocalDateTime createdAt;

    /**
     * The status of the order.
     */
    private OrderStatus status;

    /**
     * Constructs a new OrderItemsWrapper with the specified products, quantities, creation time, and status.
     *
     * @param products the list of products in the order
     * @param quantities the list of quantities for each product
     * @param createdAt the timestamp when the order was created
     * @param status the status of the order
     */
    public OrderItemsWrapper(List<ProductDTO> products, List<Integer> quantities, LocalDateTime createdAt, OrderStatus status) {
        this.products = products;
        this.quantities = quantities;
        this.createdAt = createdAt;
        this.status = status;
    }
}
