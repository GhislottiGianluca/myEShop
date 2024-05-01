package com.example.myEShop.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
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
    private String description;
    private String author;

    public Product() {
    }

    public Product(Long id,
                   String title,
                   String description,
                   String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public Product(String title,
                   String description,
                   String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
