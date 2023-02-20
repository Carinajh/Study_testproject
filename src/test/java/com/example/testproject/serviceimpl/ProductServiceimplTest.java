package com.example.testproject.serviceimpl;

import com.example.testproject.data.dto.ProductDTO;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.impl.ProductDataHandlerImpl;
import com.example.testproject.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceimplTest {

    @MockBean
    ProductDataHandlerImpl productDataHandler;

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest(){
        //given
        Mockito.when(productDataHandler.getProductEntity("123"))//목키토 언제? getproductEntity 123을 실행하려고할때 아래의 return 을 되돌려줌
                .thenReturn(new ProductEntity("123","pen",2000,1000));
        ProductDTO productDTO = productService.getProduct("123");
        Assertions.assertEquals(productDTO.getProductId(),"123");//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductName(),"pen");//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductPrice(),2000);//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductStack(),1000);//단정문으로 결과값을 체크

        verify(productDataHandler).getProductEntity("123");//when으로 주입한동작이 실제로 움직였는지 체크
    }

    @Test
    public void saveProductTest(){
        //given
        Mockito.when(productDataHandler.saveProductEntity("123","pen",2000,1000))//목키토 언제? getproductEntity 123을 실행하려고할때 아래의 return 을 되돌려줌
                .thenReturn(new ProductEntity("123","pen",2000,1000));
        ProductDTO productDTO = productService.saveProduct("123","pen",2000,1000);

        Assertions.assertEquals(productDTO.getProductId(),"123");//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductName(),"pen");//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductPrice(),2000);//단정문으로 결과값을 체크
        Assertions.assertEquals(productDTO.getProductStack(),1000);//단정문으로 결과값을 체크

        verify(productDataHandler).saveProductEntity("123","pen",2000,1000);//when으로 주입한동작이 실제로 움직였는지 체크
    }
}
