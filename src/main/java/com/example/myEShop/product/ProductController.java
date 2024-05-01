package com.example.myEShop.product;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @PostMapping
     public void addNewProduct(@RequestBody Product product){
        productService.addNewPoduct(product);
     }

     @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long id){
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description //TODO
            ){
        productService.updateProduct(productId, title, description);
    }

}
