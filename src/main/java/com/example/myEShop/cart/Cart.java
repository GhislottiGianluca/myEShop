package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "product_id")
    private Map<Long, Integer> items;


    public Cart(AppUser user, HashMap<Long, Integer> items){
        this.user = user;
        this.items = items;
    }
}