package com.example.myEShop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository interface for managing {@link Product} entities.
 * <p>
 * This interface provides methods for CRUD operations and custom queries on Product entities.
 * </p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds a product by its ID.
     * <p>
     * This method retrieves a product entity based on the provided product ID.
     * </p>
     *
     * @param id the ID of the product
     * @return an {@link Optional} containing the product if found, or empty if not found
     */
    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findProductById(Long id);

    /**
     * Finds products by their category.
     * <p>
     * This method retrieves a list of products that belong to the specified category.
     * </p>
     *
     * @param category the category of the products
     * @return a list of products in the specified category
     */
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findProductsByCategory(String category);

    /**
     * Retrieves all products sorted by price in ascending order.
     * <p>
     * This method returns a list of products sorted by their price.
     * </p>
     *
     * @return a list of products sorted by price
     */
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> getProductsByPrice();

    /**
     * Retrieves all products sorted by the number of units sold in ascending order.
     * <p>
     * This method returns a list of products sorted by the number of units sold.
     * </p>
     *
     * @return a list of products sorted by units sold
     */
    @Query("SELECT p FROM Product p ORDER BY p.sold ASC")
    List<Product> getProductsBySold();

    /**
     * Retrieves products in a specified category sorted by price in ascending order.
     * <p>
     * This method returns a list of products in the specified category sorted by their price.
     * </p>
     *
     * @param category the category of the products
     * @return a list of products in the specified category sorted by price
     */
    @Query("SELECT p FROM Product p WHERE p.category = ?1 ORDER BY p.price")
    List<Product> getProductsByPriceWithCategory(String category);

    /**
     * Retrieves products in a specified category sorted by the number of units sold in ascending order.
     * <p>
     * This method returns a list of products in the specified category sorted by the number of units sold.
     * </p>
     *
     * @param category the category of the products
     * @return a list of products in the specified category sorted by units sold
     */
    @Query("SELECT p FROM Product p WHERE p.category = ?1 ORDER BY p.sold")
    List<Product> getProductsBySoldWithCategory(String category);

    /**
     * Retrieves the top four best-selling products.
     * <p>
     * This method returns a list of the top four products with the highest number of units sold.
     * </p>
     *
     * @return a list of the top four best-selling products
     */
    @Query("SELECT p FROM Product p ORDER BY p.sold DESC LIMIT 4")
    List<Product> getBestFourProductForSales();
}
