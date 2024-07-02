package com.example.myEShop.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @BeforeEach
    void setUp() {
        Product p1 = new ProductBuilder("title")
                .description("description").category(ProductCategories.CUP).price(30.00).sold(10).build();
        Product p2 = new ProductBuilder("title")
                .description("description").category(ProductCategories.CUP).price(10.00).sold(15).build();
        Product p3 = new ProductBuilder("title")
                .description("description").category(ProductCategories.TSHIRT).price(29.00).sold(100).build();
        Product p4 = new ProductBuilder("title")
                .description("description").category(ProductCategories.TSHIRT).price(11.00).sold(32).build();
        Product p5 = new ProductBuilder("title")
                .description("description").category(ProductCategories.SWEATSHIRT).price(1.00).sold(45).build();
        Product p6 = new ProductBuilder("title")
                .description("description").category(ProductCategories.SWEATSHIRT).price(100.00).sold(1).build();

        underTest.saveAll(List.of(p1,p2,p3,p4,p5,p6));
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindProductsByCategory() {

        // Given & When
        List<Product> cups = underTest.findProductsByCategory("CUPS");
        List<Product> tshirts = underTest.findProductsByCategory("TSHIRT");
        List<Product> sweatshirts = underTest.findProductsByCategory("SWEATSHIRT");

        // Then
        for (Product cup : cups) {
            assertEquals("CUPS", cup.getCategory());
        }

        for (Product tshirt : tshirts) {
            assertEquals("TSHIRT", tshirt.getCategory());
        }

        for (Product sweatshirt : sweatshirts) {
            assertEquals("SWEATSHIRT", sweatshirt.getCategory());
        }
    }

    @Test
    void itShouldGetProductsByPrice() {
        // Given & When
        List<Product> byPrice = underTest.getProductsByPrice();

        // Then
        for (int i = 0; i < byPrice.size() - 1; i++) {
            Product current = byPrice.get(i);
            Product next = byPrice.get(i + 1);

            assertTrue(current.getPrice() <= next.getPrice());
        }
    }

    @Test
    void itShouldGetProductsBySold() {
        // Given & When
        List<Product> bySold = underTest.getProductsBySold();

        // Then
        for (int i = 0; i < bySold.size() - 1; i++) {
            Product current = bySold.get(i);
            Product next = bySold.get(i + 1);

            assertTrue(current.getSold() <= next.getSold());
        }
    }

    private void verifyProductsByPriceAndCategory(String category) {
        // Given & When
        List<Product> products = underTest.getProductsByPriceWithCategory(category);

        // Then
        for (int i = 0; i < products.size() - 1; i++) {
            Product current = products.get(i);
            Product next = products.get(i + 1);

            assertTrue(current.getPrice() <= next.getPrice());
            assertEquals(category, current.getCategory());
        }

        if (!products.isEmpty()) {
            assertEquals(category, products.get(products.size() - 1).getCategory());
        }
    }

    @Test
    void getProductsByPriceWithCategory() {
        verifyProductsByPriceAndCategory("CUP");
        verifyProductsByPriceAndCategory("TSHIRT");
        verifyProductsByPriceAndCategory("SWEATSHIRT");
    }

    private void verifyProductsBySoldAndCategory(String category) {

        // Given & When
        List<Product> products = underTest.getProductsBySoldWithCategory(category);

        // Then
        for (int i = 0; i < products.size() - 1; i++) {
            Product current = products.get(i);
            Product next = products.get(i + 1);

            assertTrue(current.getSold() >= next.getSold());
            assertEquals(category, current.getCategory());
        }

        if (!products.isEmpty()) {
            assertEquals(category, products.get(products.size() - 1).getCategory());
        }
    }

    @Test
    void getProductsBySoldWithCategory() {
        verifyProductsBySoldAndCategory("CUP");
        verifyProductsBySoldAndCategory("TSHIRT");
        verifyProductsBySoldAndCategory("SWEATSHIRT");
    }

    @Test
    void getBestFourProductForSales() {
        // Given & When
        List<Product> bestSellers = underTest.getBestFourProductForSales();

        //Then
        List<Product> allProducts = underTest.findAll();
        allProducts.sort((p1, p2) -> Integer.compare(p2.getSold(), p1.getSold()));
        List<Product> topFourExpected = new ArrayList<>(allProducts.subList(0, Math.min(4, allProducts.size())));

        assertEquals(4, bestSellers.size());

        for (int i = 0; i < topFourExpected.size(); i++) {
            assertEquals(topFourExpected.get(i).getId(), bestSellers.get(i).getId());
        }
    }
}