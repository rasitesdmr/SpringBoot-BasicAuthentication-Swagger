package com.example.springbootbasicauthenticationswagger.service;

import com.example.springbootbasicauthenticationswagger.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product createProduct(Product product);

}
