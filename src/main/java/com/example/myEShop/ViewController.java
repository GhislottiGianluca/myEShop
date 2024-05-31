package com.example.myEShop;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class ViewController {

    @GetMapping("/home")
    public String handlingWelcome() {
        return "account";
    }

    @GetMapping("/admin/home")
    public String handlingAdminHome() {
        return "index";
    }

    @GetMapping("/user/home")
    public String handlingUserHome() {
        return "index";
    }

    @GetMapping("/attending-confirmation")
    public String success() {
        return "attending-confirmation";
    }

    @GetMapping("/login")
    public String handleLoginPage(){return "account";}

    @GetMapping("/cart")
    public String handleCartPage(){return "cart";}

    @GetMapping("/products")
    public String handleProductPage(){return "products";}

    @GetMapping("/products/{category}")
    public String handleCategoryProductPage(){return "products";}

    @GetMapping("/product-details/{id}")
    public String handleProductDetails(){return "product-details";}
}
