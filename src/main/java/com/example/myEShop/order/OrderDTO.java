package com.example.myEShop.order;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderDTO (
        Map<Long, Integer> items,
        LocalDateTime createdAt,
        OrderStatus status

){}
