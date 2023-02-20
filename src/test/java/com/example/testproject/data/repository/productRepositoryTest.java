package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ProductEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@SpringBootTest
public class productRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @BeforeEach //태스트전에 데이터를 넣어주는 작업.?
    void GenerateData(){
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count),count++,2000,3000));
        productRepository.save(getProduct(Integer.toString(count),count++,3000,9000));
        productRepository.save(getProduct(Integer.toString(--count),count=count +2,1500,200));
        productRepository.save(getProduct(Integer.toString(count),count++,4000,5000));
        productRepository.save(getProduct(Integer.toString(count),count++,10000,1500));
        productRepository.save(getProduct(Integer.toString(count),count++,1000,1000));
        productRepository.save(getProduct(Integer.toString(count),count++,500,10000));
        productRepository.save(getProduct(Integer.toString(count),count++,8500,3500));
        productRepository.save(getProduct(Integer.toString(count),count++,7200,2000));
        productRepository.save(getProduct(Integer.toString(count),count++,5100,1700));

    }

    private ProductEntity getProduct(String id,int nameNumber,int price,int stack){
        return new ProductEntity(id,"상품" +nameNumber,price,stack);
    }
    @Test
    void findTest(){
        List<ProductEntity> foundAll =productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");

        List<ProductEntity> foundEntities = productRepository.findByProductName("상품4");
        for(ProductEntity productEntity : foundEntities){
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");
        for(ProductEntity productEntity : queryEntities){
            System.out.println(productEntity.toString());
        }
    }
    @Test
    void existTest(){
        List<ProductEntity> foundAll =productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        System.out.println(productRepository.existsByProductName("상품4"));
        System.out.println(productRepository.existsByProductName("상품2"));
    }
    @Test
    void countTest(){
        List<ProductEntity> foundAll =productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        System.out.println(productRepository.countByProductName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest(){
        System.out.println("before : " + productRepository.count());
        productRepository.deleteByProductId("1");
        productRepository.deleteByProductId("9");

        System.out.println("After : "+productRepository.count());

    }

    @Test
    void topTest(){
        productRepository.save(getProduct("100",123,1500,5000));
        productRepository.save(getProduct("101",123,2500,5000));
        productRepository.save(getProduct("102",123,3500,5000));
        productRepository.save(getProduct("103",123,4500,5000));
        productRepository.save(getProduct("104",123,1000,5000));
        productRepository.save(getProduct("105",123,2000,5000));
        productRepository.save(getProduct("106",123,3000,5000));
        productRepository.save(getProduct("107",123,4000,5000));

        List<ProductEntity> foundEntities =productRepository.findFirst5ByProductName("상품123");
        for(ProductEntity productEntity : foundEntities){
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> foundEntities2 =productRepository.findTop3ByProductName("상품123");
        for(ProductEntity productEntity : foundEntities2){
            System.out.println(productEntity.toString());
        }
    }

    //     ↓↓초견자 키워드 테스트↓↓

    @Test
    void isEqualsTest(){
        List<ProductEntity> foundAll =productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        System.out.println(productRepository.findByProductIdIs("1"));
        System.out.println(productRepository.findByProductIdEquals("1"));
    }

    @Test
    void notTest(){
        List<ProductEntity> foundAll =productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        //System.out.println(productRepository.findByProductIdNot("1"));
        //System.out.println(productRepository.findByProductIdIsNot("1"));
        List<ProductEntity> list = productRepository.findByProductIdNot("1");
        for (ProductEntity productEntity: list){
            System.out.println(productEntity);
        }
        list = productRepository.findByProductIdIsNot("1");
        for (ProductEntity productEntity: list) {
            System.out.println(productEntity);
        }
    }

    @Test
    void nullTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");

        System.out.println(productRepository.findByProductStockIsNull());
        System.out.println(productRepository.findByProductStockIsNotNull());
    }

    @Test
    void andTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");

        System.out.println(productRepository.findTopByProductIdAndProductName("1","상품1"));
    }

    @Test
    void greaterTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");

        List<ProductEntity> productEntities = productRepository.findByProductPriceGreaterThan(5000);
        for (ProductEntity productEntity: productEntities) {
            System.out.println(productEntity);
        }
    }

    @Test
    void contaionTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        //like검색
        System.out.println(productRepository.findByProductNameContaining("상품1"));
    }

    //     ↓↓정렬과 페이징 테스트↓↓
    @Test
    void orderByTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc("상품");
        for(ProductEntity productEntity : foundProducts){
            System.out.println(productEntity.toString());
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for(ProductEntity productEntity : foundProducts){
            System.out.println(productEntity.toString());
        }

    }

    @Test
    void multiorderByTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc("상품");
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void orderByWithParameterTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining(
            "상품",Sort.by(Order.asc("productPrice")));
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity.toString());
        }
        foundProducts = productRepository.findByProductNameContaining(
            "상품",Sort.by(Order.asc("productPrice"),Order.asc("productStock")));
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void pagingTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("-------↓testData↓-------");
        for(ProductEntity productEntity : foundAll){
            System.out.println(productEntity.toString());
        }
        System.out.println("-------↑testData↑-------");
        List<ProductEntity> foundProducts = productRepository.findByProductPriceGreaterThan(
            200, PageRequest.of(0,2));
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity.toString());
        }
        foundProducts = productRepository.findByProductPriceGreaterThan(
            200, PageRequest.of(2,2));
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    public void queryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasis();
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasisNativeQuery();
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void parameterQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameter(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void parameterNamingQueryTest2() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void nativeQueryPagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterPaging(2000,
            PageRequest.of(2, 2));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }





//    @Test
//    public void basicCRUDTest() {
//        /* create */
//        // given
//        Product product = Product.builder()
//            .id("testProduct")
//            .name("testP")
//            .price(1000)
//            .stock(500)
//            .build();
//
//        // when
//        Product savedEntity = productRepository.save(product);
//
//        // then
//        Assertions.assertThat(savedEntity.getId())
//            .isEqualTo(product.getId());
//        Assertions.assertThat(savedEntity.getName())
//            .isEqualTo(product.getName());
//        Assertions.assertThat(savedEntity.getPrice())
//            .isEqualTo(product.getPrice());
//        Assertions.assertThat(savedEntity.getStock())
//            .isEqualTo(product.getStock());
//
//        /* read */
//        // when
//        Product selectedEntity = productRepository.findById("testProduct")
//            .orElseThrow(RuntimeException::new);
//
//        // then
//        Assertions.assertThat(selectedEntity.getId())
//            .isEqualTo(product.getId());
//        Assertions.assertThat(selectedEntity.getName())
//            .isEqualTo(product.getName());
//        Assertions.assertThat(selectedEntity.getPrice())
//            .isEqualTo(product.getPrice());
//        Assertions.assertThat(selectedEntity.getStock())
//            .isEqualTo(product.getStock());
//    }
}
