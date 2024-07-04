package com.example.myEShop.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing orders.
 * <p>
 * This controller provides endpoints for retrieving and creating orders.
 * </p>
 */
@RestController
@RequestMapping(path="/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs a new OrderController with the specified OrderService.
     *
     * @param orderService the service for managing orders
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves the list of orders for the current user.
     * @return a list of {@link OrderItemsWrapper} representing the user's orders
     */
    @GetMapping("/getOrders")
    public List<OrderItemsWrapper> getOrders() {
        return orderService.getOrders();
    }

    /**
     * Creates a new order for the current user.
     */
    @PostMapping("/createOrder")
    public void handlingCreationOfOrder() {
        orderService.createOrder();
    }
}
