package com.example.testproject.controller;

import com.example.testproject.common.Constants;
import com.example.testproject.common.exception.MeException;
import com.example.testproject.data.dto.ProductDTO;
import com.example.testproject.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private ProductService productService;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    @GetMapping("/product/{productId}")
    public ProductDTO getProduct(@PathVariable String productId){

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} testproject API.","getProduct");

        ProductDTO  productDTO = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId={},productName={},productPrice={}" +
                "productStack={},response Time={}ms",productDTO.getProductId(),productDTO.getProductName()
        ,productDTO.getProductPrice(),productDTO.getProductStack(),System.currentTimeMillis()-startTime);

        return productDTO;
    }

    @PostMapping("/product")
    //public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO){
    public ProductDTO createProduct(ProductDTO productDTO){
        String productId = productDTO.getProductId();
        String productName= productDTO.getProductName();
        int productPrice = productDTO.getProductPrice();
        int productStack = productDTO.getProductStack();

        LOGGER.info("[createProduct] Response :: productId={},productName={},productPrice={}" +
                        "productStack={}",productDTO.getProductId(),productDTO.getProductName()
                ,productDTO.getProductPrice(),productDTO.getProductStack());


        return productService.saveProduct(productId,productName,productPrice,productStack);

    }




    @DeleteMapping("/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId){
        return null;
    }
    @PostMapping("/product/exception")
    public void exceptionTest() throws MeException{
        throw new MeException(Constants.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN,"의도한 에러가 발생하였음");
    }
}
