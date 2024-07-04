package com.example.myEShop.order;

/**
 * Enumeration representing the status of an order.
 * <p>
 * This enum defines the possible states that an order can be in during its lifecycle.
 * </p>
 */
public enum OrderStatus {

    /**
     * The order has been created but not yet processed.
     */
    CREATED,

    /**
     * The order has been shipped but not yet delivered.
     */
    SHIPPED,

    /**
     * The order has been delivered to the customer.
     */
    DELIVERED
}
