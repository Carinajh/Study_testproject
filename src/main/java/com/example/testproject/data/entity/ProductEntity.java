package com.example.testproject.data.entity;

import com.example.testproject.data.dto.ProductDTO;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //이 클래스는 엔티티임을 나타냄
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name ="product") //엔티티를 기반으로 데이터베이스에 태이블을 자동생성
public class ProductEntity extends BaseEntity{

    @Id //디비PK선언 자바에서는 ID라고함.
    String productId;
    String productName;
    Integer productPrice;
    Integer productStock;

    public ProductDTO toDto(){
        return ProductDTO.builder()
            .productId(productId)
            .productName(productName)
            .productPrice(productPrice)
            .productStack(productStock)
            .build();
    }

}
