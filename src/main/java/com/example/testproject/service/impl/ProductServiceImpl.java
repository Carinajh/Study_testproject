package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDTO;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.ProductDataHandler;
import com.example.testproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductDataHandler productDataHandler;

    @Autowired
    public ProductServiceImpl(ProductDataHandler productDataHandler) {
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDTO saveProduct(String productId, String productName, int productPrice, int productStack) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId,productName,productPrice,productStack);
        ProductDTO productDTO = new ProductDTO(productEntity.getProductId()
                ,productEntity.getProductName()
                ,productEntity.getProductPrice()
                ,productEntity.getProductStock());

        return productDTO;
    }

    @Override
    public ProductDTO getProduct(String productId) {
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);
        ProductDTO productDTO = new ProductDTO(productEntity.getProductId()
                ,productEntity.getProductName()
                ,productEntity.getProductPrice()
                ,productEntity.getProductStock());

        return productDTO;
    }
}
