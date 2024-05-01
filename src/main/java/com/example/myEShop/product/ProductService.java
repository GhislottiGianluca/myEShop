package com.example.myEShop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public void addNewPoduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exist = productRepository.existsById(id);
        if(!exist) {
            throw new IllegalStateException("Product with id: " + id + " does not exists.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long productId, String title, String description) {

        Product p = productRepository.findProductById(productId)
                .orElseThrow(() -> new IllegalStateException(
                        "Product with id: " + productId + "does not exist"
                ));

        if(title != null
                && !title.isEmpty()
                && !Objects.equals(p.getTitle(), title)){
            p.setTitle(title);
        }

        if(description != null
                && !description.isEmpty()
                && !Objects.equals(p.getDescription(), description)){
            p.setDescription(description);
        }
    }
}
