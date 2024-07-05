package com.example.myEShop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 * <p>
 * This controller provides endpoints for retrieving, adding, updating, and deleting products,
 * as well as retrieving products based on various criteria such as category, price, and sales.
 * </p>
 */
@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new ProductController with the specified ProductService.
     *
     * @param productService the service for managing products
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of {@link ProductDTO} representing all products
     */
    @GetMapping
    public List<ProductDTO> getProduct() {
        return productService.getProduct();
    }

    /**
     * Retrieves products by category.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category
     */
    @GetMapping(path = "/{category}")
    public List<ProductDTO> getProduct(@PathVariable("category") String category) {
        return productService.getProductByCategory(category);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the {@link ProductDTO} representing the product with the specified ID
     */
    @GetMapping(path = "/getById/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Adds a new product.
     *
     * @param product the product to add
     */
    @PostMapping
    public void addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    @DeleteMapping(path = "/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    /**
     * Updates an existing product.
     *
     * @param productId the ID of the product to update
     * @param title the new title of the product
     * @param description the new description of the product
     */
    @PutMapping(path = "/{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        productService.updateProduct(productId, title, description);
    }

    /**
     * Retrieves products by category and sorts them by price.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category sorted by price
     */
    @GetMapping(path = "/{category}/getByPrice")
    public List<ProductDTO> getByPrice(@PathVariable("category") String category) {
        return productService.getProductsByPriceWithCategory(category);
    }

    /**
     * Retrieves all products sorted by price.
     *
     * @return a list of {@link ProductDTO} representing all products sorted by price
     */
    @GetMapping(path = "/getByPrice")
    public List<ProductDTO> getByPrice() {
        return productService.getProductsByPrice();
    }

    /**
     * Retrieves products by category and sorts them by sales.
     *
     * @param category the category of the products
     * @return a list of {@link ProductDTO} representing the products in the specified category sorted by sales
     */
    @GetMapping(path = "/{category}/getBySold")
    public List<ProductDTO> getBySold(@PathVariable("category") String category) {
        return productService.getProductsBySoldWithCategory(category);
    }

    /**
     * Retrieves all products sorted by sales.
     *
     * @return a list of {@link ProductDTO} representing all products sorted by sales
     */
    @GetMapping(path = "/getBySold")
    public List<ProductDTO> getBySold() {
        return productService.getProductsBySold();
    }

    /**
     * Retrieves the best-selling products.
     *
     * @return a list of {@link ProductDTO} representing the best-selling products
     */
    @GetMapping(path = "/getBestSellers")
    public List<ProductDTO> getBestSellers() {
        return productService.getBestFourProductsForSales();
    }

    /**
     * Retrieves all cup products.
     *
     * @return a list of {@link ProductDTO} representing all cup products
     */
    @GetMapping(path = "/getCupsProducts")
    public List<ProductDTO> getCupsProduct() {
        return productService.getProductByCategory("CUP");
    }

    /**
     * Retrieves all T-shirt products.
     *
     * @return a list of {@link ProductDTO} representing all T-shirt products
     */
    @GetMapping(path = "/getTShirtProducts")
    public List<ProductDTO> getTShirtProduct() {
        return productService.getProductByCategory("TSHIRT");
    }

    /**
     * Retrieves all sweatshirt products.
     *
     * @return a list of {@link ProductDTO} representing all sweatshirt products
     */
    @GetMapping(path = "/getSweatshirtProducts")
    public List<ProductDTO> getSweatshirtProduct() {
        return productService.getProductByCategory("SWEATSHIRT");
    }
}
