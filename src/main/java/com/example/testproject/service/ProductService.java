package com.example.testproject.service;

import com.example.testproject.data.dto.ProductDTO;

public interface ProductService {

    ProductDTO saveProduct(String productId,String productName,int productPrice,int productStack);

    ProductDTO getProduct(String productId);
}
