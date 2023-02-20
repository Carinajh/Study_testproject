package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ProductEntity;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    //JpaRepository 를사용함하여 CRUD를 가능하게함.

    /* *****쿼리 매소드의 주제 키워드***** */
    //조회
    List<ProductEntity> findByProductName(String name);
    List<ProductEntity> queryByProductName(String name);

    //존재 유무
    boolean existsByProductName(String name);


    //쿼리결과 갯수
    long countByProductName(String name);

    //삭제
    void deleteByProductId(String id);
    long removeByProductId(String id);

    //값 갯수 제한
    List<ProductEntity> findFirst5ByProductName(String name);
    List<ProductEntity> findTop3ByProductName(String name);

    /* *****쿼리 매소드의 조건자 키워드***** */
    /*
    Is , Equals 생략가능
    Logical Keyword : IS , Keyword Expressions : Is, Equals, (or no keyword)
    findByNumber 메소드와 동일하게 동작
     */

    ProductEntity findByProductIdIs(String id);
    ProductEntity findByProductIdEquals(String id);
    ProductEntity findByProductId(String id);

    //(Is)Not
    List<ProductEntity> findByProductIdNot(String id);
    List<ProductEntity> findByProductIdIsNot(String id);

    //(Is)Null,(Is)NotNull
    List<ProductEntity> findByProductStockIsNull();
    List<ProductEntity> findByProductStockIsNotNull();

    //And,Or
    List<ProductEntity> findTopByProductIdAndProductName(String id,String name);

    //(Is)GreateThan,(Is)LessThan,(Is)Between
    List<ProductEntity> findByProductPriceGreaterThan(Integer price);

    //(Is)Like,(Is)Containing,(Is)StartingWith,(Is)EndingWith
    List<ProductEntity> findByProductNameContaining(String name);


    /* ***** 정렬과 페이징 ***** */

    //ASC : 오름차순,Desc:내림차순
    List<ProductEntity> findByProductNameContainingOrderByProductStockAsc(String name);
    List<ProductEntity> findByProductNameContainingOrderByProductStockDesc(String name);
    //여러정렬기준사용
    List<ProductEntity> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String name);
    //매개변수를활용한정렬
    List<ProductEntity> findByProductNameContaining(String name, Sort sort);
    //페이징 처리하기
    List<ProductEntity> findByProductPriceGreaterThan(Integer price, Pageable pageable);


    /* ***** @Query 사용하기 ***** */

//    @Query(value = "SELECT p FROM Product p",nativeQuery = false)
    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > 2000")
    List<ProductEntity> findByPriceBasis();

    @Query(value = "SELECT * FROM Product p WHERE p.product_Price > 2000",nativeQuery = true)
    List<ProductEntity> findByPriceBasisNativeQuery();

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > ?1")
    List<ProductEntity> findByPriceWithParameter(Integer productPrice);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :productPrice")
    List<ProductEntity> findByPriceWithParameterNaming(Integer productPrice);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :pri")
    List<ProductEntity> findByPriceWithParameterNaming2(@Param("pri") Integer productPrice);

//    @Query(value = "SELECT p FROM ProductEntity p WHERE p.productPrice > 2000",
//    countQuery = "SELECT count(*) FROM Product WHERE product_Price > ?1",
//    nativeQuery = true)
    @Query(value = "SELECT * FROM Product p WHERE p.product_Price > :productPrice",
    countQuery = "SELECT count(*) FROM Product p WHERE p.product_Price >?1",
    nativeQuery = true)
    List<ProductEntity> findByPriceWithParameterPaging(Integer productPrice,Pageable pageable);
}
