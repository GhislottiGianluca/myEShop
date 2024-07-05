package com.example.myEShop.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Entity class representing a product in the eShop system.
 * <p>
 * This class maps to the "product" table in the database and contains information
 * about the product such as its title, description, category, images, price,
 * currency, stock status, and creation time.
 * </p>
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    /**
     * The unique identifier for the product.
     */
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    /**
     * The timestamp when the product was created.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationTime;

    /**
     * The title of the product.
     */
    private String title;

    /**
     * The description of the product.
     * <p>
     * This field is stored as a large object (LOB) in the database.
     * </p>
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The category of the product.
     */
    private String category;

    /**
     * The primary image URL of the product.
     */
    private String image1;

    /**
     * The secondary image URL of the product.
     */
    private String image2;

    /**
     * The tertiary image URL of the product.
     */
    private String image3;

    /**
     * The quaternary image URL of the product.
     */
    private String image4;

    /**
     * The price of the product.
     */
    private double price;

    /**
     * The currency of the product's price.
     */
    private String currency;

    /**
     * The quantity of the product remaining in stock.
     */
    private int remained;

    /**
     * The quantity of the product sold.
     */
    private int sold;

    /**
     * Default constructor that initializes the creation time to the current time.
     */
    public Product() {
        this.creationTime = LocalDateTime.now();
    }

    /**
     * Constructs a new Product with the specified details.
     *
     * @param id the unique identifier for the product
     * @param title the title of the product
     * @param description the description of the product
     * @param category the category of the product
     * @param image1 the primary image URL of the product
     * @param image2 the secondary image URL of the product
     * @param image3 the tertiary image URL of the product
     * @param image4 the quaternary image URL of the product
     * @param price the price of the product
     * @param currency the currency of the product's price
     * @param remained the quantity of the product remaining in stock
     * @param sold the quantity of the product sold
     */
    public Product(Long id,
                   String title,
                   String description,
                   String category,
                   String image1,
                   String image2,
                   String image3,
                   String image4,
                   double price,
                   String currency,
                   int remained,
                   int sold) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.price = price;
        this.currency = currency;
        this.remained = remained;
        this.sold = sold;
        this.creationTime = LocalDateTime.now();
    }

    /**
     * Constructs a new Product with the specified details, without an ID.
     *
     * @param title the title of the product
     * @param description the description of the product
     * @param category the category of the product
     * @param image1 the primary image URL of the product
     * @param image2 the secondary image URL of the product
     * @param image3 the tertiary image URL of the product
     * @param image4 the quaternary image URL of the product
     * @param price the price of the product
     * @param currency the currency of the product's price
     * @param remained the quantity of the product remaining in stock
     * @param sold the quantity of the product sold
     */
    public Product(String title,
                   String description,
                   String category,
                   String image1,
                   String image2,
                   String image3,
                   String image4,
                   double price,
                   String currency,
                   int remained,
                   int sold) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.price = price;
        this.currency = currency;
        this.remained = remained;
        this.sold = sold;
        this.creationTime = LocalDateTime.now();
    }
}
