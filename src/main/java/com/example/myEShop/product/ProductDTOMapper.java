package com.example.myEShop.product;

import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 * Service class for mapping {@link Product} entities to {@link ProductDTO} data transfer objects.
 * <p>
 * This class implements the {@link Function} interface to provide a method
 * for converting a Product entity to a ProductDTO.
 * </p>
 */
@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    /**
     * Applies this function to the given argument.
     * <p>
     * This method converts a {@link Product} entity to a {@link ProductDTO}.
     * </p>
     *
     * @param product the Product entity to convert
     * @return the converted ProductDTO
     */
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getImage1(),
                product.getImage2(),
                product.getImage3(),
                product.getImage4(),
                product.getPrice(),
                product.getCurrency());
    }
}
