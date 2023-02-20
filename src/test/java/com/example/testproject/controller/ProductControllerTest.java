package com.example.testproject.controller;

import com.example.testproject.data.dto.ProductDTO;
import com.example.testproject.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


import static org.mockito.BDDMockito.given;

//@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; //웹 API 테스트(GET, POST 등 API 테스트)

    //ProductServiceImpl에서 잡고있는 bean 객체에 대해 Mock형태의 객체를 생성해줌
    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{
        //given:Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 매소드
        given(productService.getProduct("12315")).willReturn(
                new ProductDTO("52471","pen",5000,1000));

        String productId="12315";

        // andExpect : 기대하는 값이 나왔는지 체그해볼 수 있는 매소드
        mockMvc.perform(
                get("/api/v1/product-api/product/"+productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStack").exists())
                .andDo(print());

        //verify : 해당 객체의 매소드가 실행되었는지 체크해줌
        verify(productService).getProduct("12315");
    }

//    @Test
//    @DisplayName("product 데이터 생성 테스트")
//    void createProductTest() throws Exception{
//        //given:Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 매소드
//        given(productService.saveProduct("15871","pen",5000,2000)).willReturn(
//                new ProductDTO("15871","pen",5000,2000));
//
//        ProductDTO productDTO = ProductDTO.builder().productId("15871").productName("pen")
//                .productPrice(5000).productStack(2000).build();
//
//        Gson gson = new Gson();
//        String content = gson.toJson(productDTO);//JSON을 스트링형태로 변환
//
//        //아래 코드로 json스트링형태로 변환 작업을 대체할 수 있음
//        String json = new ObjectMapper().writeValueAsString(productDTO);
//
//        mockMvc.perform(
//                post("/api/v1/product-api/product")
//                        .content(content)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productId").exists())
//                .andExpect(jsonPath("$.productName").exists())
//                .andExpect(jsonPath("$.productPrice").exists())
//                .andExpect(jsonPath("$.productStack").exists())
//                .andDo(print());
//        verify(productService).saveProduct("15871","pen",5000,2000);
//    }
    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(productService.saveProduct("15871", "pen", 500, 200))
                .willReturn(new ProductDTO("15871", "pen", 500, 200));

        ProductDTO productDto = ProductDTO.builder().productId("15871").productName("pen")
                .productPrice(500).productStack(200).build();
        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        // 아래 코드로 json 형태 변경 작업을 대체할 수 있음
         String json = new ObjectMapper().writeValueAsString(productDto);
        System.out.printf(json);
        mockMvc.perform(
                        post("/api/v1/product-api/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStack").exists())
                .andDo(print());

        verify(productService).saveProduct("15871", "pen", 500, 200);
    }


}
