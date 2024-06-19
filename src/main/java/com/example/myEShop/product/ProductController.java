package com.example.myEShop.product;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProduct(){
        return productService.getProduct();
    }

    @GetMapping(path = "/{category}")
    public List<ProductDTO> getProduct(@PathVariable("category") String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping(path = "/getById/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {return productService.getProductById(id);}


    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id){
        productService.deleteProduct(id);
    }

    @PutMapping(path = "/{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description //TODO
            ){
        productService.updateProduct(productId, title, description);
    }

    @GetMapping(path="/{category}/getByPrice")
    public List<ProductDTO> getByPrice(@PathVariable("category") String category){return productService.getProductsByPriceWithCategory(category);}

    @GetMapping(path="/getByPrice")
    public List<ProductDTO> getByPrice(){return productService.getProductsByPrice();}

    @GetMapping(path="/{category}/getBySold")
    public List<ProductDTO> getBySold(@PathVariable("category") String category){return productService.getProductsBySoldWithCategory(category);}

    @GetMapping(path="/getBySold")
    public List<ProductDTO> getBySold(){return productService.getProductsBySold();}

    @GetMapping(path="/getBestSellers")
    public List<ProductDTO> getBestSellers(){return productService.getBestFourProductsForSales();}

    @GetMapping(path="/getCupsProducts")
    public List<ProductDTO> getCupsProduct(){return productService.getProductByCategory("CUP");}

    @GetMapping(path="/getTShirtProducts")
    public List<ProductDTO> getTShirtProduct(){return productService.getProductByCategory("TSHIRT");}

    @GetMapping(path="/getSweatshirtProducts")
    public List<ProductDTO> getSweatshirtProduct(){return productService.getProductByCategory("SWEATSHIRT");}

}
