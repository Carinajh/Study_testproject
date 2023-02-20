package com.example.testproject.data.dao.impl;

import com.example.testproject.data.dao.ProductDAO;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductDAOImpl implements ProductDAO {

    ProductRepository productRepository;

    @Autowired //객체를 주입받는다.DI 의존성주입(미리 만들어져있는것을 가져다씀)
    public  ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        //DB저장
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        //ID값으로 DBSelect결과 가져옴
        ProductEntity productEntity = productRepository.getById(productId);
        return productEntity;
    }
}
