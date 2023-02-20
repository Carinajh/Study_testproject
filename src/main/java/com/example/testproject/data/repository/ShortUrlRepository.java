package com.example.testproject.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.testproject.data.entity.ShortUrlEntity;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {
    //JpaRepository의 기본매소드 사용 . 쿼리실행
    ShortUrlEntity findByUrl(String url);

    ShortUrlEntity findByOrgUrl(String originalUrl);


}
