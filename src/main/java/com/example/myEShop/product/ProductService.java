package com.example.myEShop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public ProductService(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    public List<ProductDTO> getProduct(){
        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public void addNewProduct(Product product) {
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

    public List<ProductDTO> getProductsByPrice(){
        return productRepository.getProductsByPrice()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsBySold(){
        return productRepository.getProductsBySold()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

}
