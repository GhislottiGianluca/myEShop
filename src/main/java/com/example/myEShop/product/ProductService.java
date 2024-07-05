package com.example.myEShop.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing products.
 * <p>
 * This service handles the business logic for product operations such as retrieval,
 * creation, deletion, and updates. It also provides methods to get products by
 * various criteria like price, sales, and category.
 * </p>
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    /**
     * Constructs a new ProductService with the specified dependencies.
     *
     * @param productRepository the repository for managing product entities
     * @param productDTOMapper the mapper for converting Product entities to ProductDTOs
     */
    public ProductService(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of {@link ProductDTO} representing all products
     */
    public List<ProductDTO> getProduct() {
        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the {@link ProductDTO} representing the product with the specified ID, or null if not found
     */
    public ProductDTO getProductById(Long id) {
        Optional<Product> p = productRepository.findProductById(id);
        return p.map(productDTOMapper).orElse(null);
    }

    /**
     * Adds a new product.
     *
     * @param product the product to add
     */
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @throws IllegalStateException if the product with the specified ID does not exist
     */
    public void deleteProduct(Long id) {
        boolean exist = productRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Product with id: " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates the details of an existing product.
     *
     * @param id the ID of the product to update
     * @param title the new title of the product
     * @param description the new description of the product
     * @throws IllegalStateException if the product with the specified ID does not exist
     */
    @Transactional
    public void updateProduct(Long id, String title, String description) {
        Product p = productRepository.findProductById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Product with title: " + title + " and ID: " + id + " does not exist"
                ));

        if (title != null && !title.isEmpty() && !Objects.equals(p.getTitle(), title)) {
            p.setTitle(title);
        }

        if (description != null && !description.isEmpty() && !Objects.equals(p.getDescription(), description)) {
            p.setDescription(description);
        }
    }

    /**
     * Retrieves all products sorted by price in ascending order.
     *
     * @return a list of {@link ProductDTO} representing all products sorted by price
     */
    public List<ProductDTO> getProductsByPrice() {
        return productRepository.getProductsByPrice()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all products sorted by the number of units sold in ascending order.
     *
     * @return a list of {@link ProductDTO} representing all products sorted by units sold
     */
    public List<ProductDTO> getProductsBySold() {
        return productRepository.getProductsBySold()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the top four best-selling products.
     *
     * @return a list of {@link ProductDTO} representing the top four best-selling products
     */
    public List<ProductDTO> getBestFourProductsForSales() {
        return productRepository.getBestFourProductForSales()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves products by category.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category
     */
    public List<ProductDTO> getProductByCategory(String category) {
        return productRepository.findProductsByCategory(category)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves products in a specified category sorted by price in ascending order.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category sorted by price
     */
    public List<ProductDTO> getProductsByPriceWithCategory(String category) {
        return productRepository.getProductsByPriceWithCategory(category)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves products in a specified category sorted by the number of units sold in ascending order.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category sorted by units sold
     */
    public List<ProductDTO> getProductsBySoldWithCategory(String category) {
        return productRepository.getProductsBySoldWithCategory(category)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves products by their IDs.
     *
     * @param productIds the set of product IDs to retrieve
     * @return a list of {@link ProductDTO} representing the products with the specified IDs
     */
    public List<ProductDTO> getProductsByIds(Set<Long> productIds) {
        return productIds.stream()
                .map(this::getProductById)
                .collect(Collectors.toList());
    }
}
