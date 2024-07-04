package com.example.myEShop.order;

import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class for mapping {@link Order} entities to {@link OrderDTO} data transfer objects.
 * <p>
 * This class implements the {@link Function} interface to provide a method
 * for converting an Order entity to an OrderDTO.
 * </p>
 */
@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    /**
     * Applies this function to the given argument.
     * <p>
     * This method converts an {@link Order} entity to an {@link OrderDTO}.
     * </p>
     *
     * @param order the Order entity to convert
     * @return the converted OrderDTO
     */
    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getItems(),
                order.getCreatedAt(),
                order.getStatus());
    }
}
