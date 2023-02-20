package com.example.testproject.data.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.testproject.data.dto.ShortUrlResponseDto;

/**
 * PackageName : com.example.testproject.data.repository
 * FileName : ShortUrlRedisRepository
 * Author : Flature
 * Date : 2022-05-21
 * Description :
 */
public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String> {

}