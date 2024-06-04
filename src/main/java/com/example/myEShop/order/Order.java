package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.product.Product;
import com.example.myEShop.order.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> items;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(AppUser appUser, Map<Long, Integer> items, OrderStatus status){
        this.appUser = appUser;
        this.items = items;
        this.status = status;
    }
}
