package com.example.springbootbasicauthenticationswagger.controller;

import com.example.springbootbasicauthenticationswagger.model.Product;
import com.example.springbootbasicauthenticationswagger.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listProduct")
    @SecurityRequirement(name = "basic-auth")
    public ResponseEntity<List<Product>>getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    @SecurityRequirement(name = "basic-auth")
    public ResponseEntity<Product> createProduct(Product product){
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }
}
