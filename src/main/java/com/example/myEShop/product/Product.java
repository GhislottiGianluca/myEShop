package com.example.myEShop.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Product {
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
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationTime;
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private double price;
    private String currency;
    private int remained;
    private int sold;

    public Product() {
    }

    public Product(Long id,
                   String title,
                   String description,
                   String category,
                   String image1,
                   String image2,
                   String image3,
                   String image4,
                   String image5,
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
        this.image5 = image5;
        this.price = price;
        this.currency = currency;
        this.remained = remained;
        this.sold = sold;
    }

    public Product(String title,
                   String description,
                   String category,
                   String image1,
                   String image2,
                   String image3,
                   String image4,
                   String image5,
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
        this.image5 = image5;
        this.price = price;
        this.currency = currency;
        this.remained = remained;
        this.sold = sold;
    }
}
