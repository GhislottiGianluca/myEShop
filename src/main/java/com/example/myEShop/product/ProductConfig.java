package com.example.myEShop.product;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Configuration
@EnableJpaAuditing
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepository productRepository){
        return args -> {
            Product p1 = new Product();
            Product p2 = new Product();
            Product p3 = new Product();
            Product p4 = new Product();
            Product p5 = new Product();

            productRepository.saveAll(
                    List.of(p1, p2, p3, p4, p5));
        };
    }
}
