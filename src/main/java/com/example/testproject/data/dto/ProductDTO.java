package com.example.testproject.data.dto;

import com.example.testproject.data.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull
    //@Size(min = 0 , max = 8) //abcdefg
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    @Min(100)
    @Max(300000)
    private int productPrice;
    @NotNull
    @Min(0)
    @Max(9999)
    private int productStack;
    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStack)
                .build();
    }

}
