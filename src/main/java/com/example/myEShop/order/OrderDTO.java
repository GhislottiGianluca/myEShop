package com.example.myEShop.order;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object for order information.
 * <p>
 * This record is used to transfer order data between processes, containing details
 * about the items in the order, the creation time, and the order status.
 * </p>
 *
 * @param items a map of product IDs to their quantities in the order
 * @param createdAt the timestamp when the order was created
 * @param status the status of the order
 */
public record OrderDTO (
        Map<Long, Integer> items,
        LocalDateTime createdAt,
        OrderStatus status
) {}
