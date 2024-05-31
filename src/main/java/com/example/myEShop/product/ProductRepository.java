package com.example.myEShop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findProductsByCategory(String category);

    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> getProductsByPrice();

    @Query("SELECT p FROM Product p WHERE p.category = ?1 ORDER BY p.price")
    List<Product> getProductsByPriceWithCategory(String category);

    @Query("SELECT p FROM Product p ORDER BY p.sold")
    List<Product> getProductsBySold();

    @Query("SELECT p FROM Product p WHERE p.category = ?1 ORDER BY p.sold")
    List<Product> getProductsBySoldWithCategory(String category);

    @Query("SELECT p FROM Product p ORDER BY p.sold DESC LIMIT 4")
    List<Product> getBestFourProductForSales();

    @Query("SELECT p FROM Product p WHERE p.category='Cups'")
    List<Product> getCupsProduct();

    @Query("SELECT p FROM Product p WHERE p.category='TShirt'")
    List<Product> getTShirtProduct();

    @Query("SELECT p FROM Product p WHERE p.category='Sweatshirt'")
    List<Product> getSweatshirtProduct();

    // TODO OTHER CASE
    
}
