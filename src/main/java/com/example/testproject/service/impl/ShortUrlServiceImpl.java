//package com.example.testproject.service.impl;
//
//import com.example.testproject.data.dto.NaverUriDto;
//import com.example.testproject.data.dto.ShortUrlResponseDto;
//import com.example.testproject.data.entity.ShortUrlEntity;
//import com.example.testproject.service.ShortUrlService;
//import java.net.URI;
//import java.util.Arrays;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Service
//public class ShortUrlServiceImpl implements ShortUrlService {
//
//    Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
//
//    @Override
//    public ShortUrlResponseDto getShortUrl(String clientId, String clientSercret,
//        String originalUrl) {
//        return null;
//    }
//
//    @Override
//    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSercret,
//        String originalUrl) {
//        LOGGER.info("[generateShortUrl] reuqest data: {}",originalUrl);
//        ResponseEntity<NaverUriDto> responseEntity =
//            requestShortUrl(clientId,clientSercret,originalUrl);
//
//
//        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
//        String shortUrl = responseEntity.getBody().getResult().getUrl();
//        String hash = responseEntity.getBody().getResult().getHash();
//
//        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
//        shortUrlEntity.setOrgUrl(orgUrl);
//        shortUrlEntity.setUrl(shortUrl);
//        shortUrlEntity.setHash(hash);
//
//        //shortUrlLOAD.saveShortUrl(shortUrlEntity);
//
//        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl,shortUrl);
//
//        return shortUrlResponseDto;
//    }
//
//    @Override
//    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSercret,
//        String originalUrl) {
//        return null;
//    }
//
//    @Override
//    public ShortUrlResponseDto deleteByShortUrl(String shortUrl) {
//        return null;
//    }
//
//    @Override
//    public ShortUrlResponseDto deleteByOriginal(String originalUrl) {
//        return null;
//    }
//
//    private ResponseEntity<NaverUriDto> requestShortUrl
//        (String clientId,String clientSecret,String originalUrl){
//        LOGGER.info("[requestShortUrl] client ID : ***. client Secret : ***. original URL : {}",
//            originalUrl);
//
//        URI uri = UriComponentsBuilder
//            .fromUriString("http://openapi.naver.com")
//            .path("/v1/util/sgorturl")
//            .queryParam("url",originalUrl)
//            .encode()
//            .build()
//            .toUri();
//
//        LOGGER.info("requestShortUrl] set Http Request Heeder");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-Naver-Client-Id", clientId);
//        headers.set("X-Naver-Client-Secret", clientSecret);
//
//        HttpEntity<String> entity = new HttpEntity<>("",headers);
//        RestTemplate restTemplate = new RestTemplate();
//
//        LOGGER.info("requestShortUrl] request by restTemplate");
//
//        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
//            entity, NaverUriDto.class);
//
//        LOGGER.info("requestShortUrl] request has been successfully complete");
//
//        return responseEntity;
//    }
//}
package com.example.testproject.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.testproject.data.dao.ShortUrlDAO;
import com.example.testproject.data.dto.NaverUriDto;
import com.example.testproject.data.dto.ShortUrlResponseDto;
import com.example.testproject.data.entity.ShortUrlEntity;
import com.example.testproject.data.repository.ShortUrlRedisRepository;
import com.example.testproject.service.ShortUrlService;

import java.net.URI;
import java.util.Arrays;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final ShortUrlDAO shortUrlDAO;
    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO, ShortUrlRedisRepository shortUrlRedisRepository) {
        this.shortUrlDAO = shortUrlDAO;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        LOGGER.info("[getShortUrl] request data : {}", originalUrl);

        // Cache Logic
        Optional<ShortUrlResponseDto> foundResponseDto = shortUrlRedisRepository.findById(originalUrl);
        if (foundResponseDto.isPresent()) {
            LOGGER.info("[getShortUrl] Cache Data existed.");
            return foundResponseDto.get();
        } else {
            LOGGER.info("[getShortUrl] Cache Data does not existed.");
        }

        ShortUrlEntity getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;
        LOGGER.info("[getShortUrl] request data : {}", getShortUrlEntity);

        if (getShortUrlEntity == null) {
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
                originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
            String hash = responseEntity.getBody().getResult().getHash();

            ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
            shortUrlEntity.setOrgUrl(orgUrl);
            shortUrlEntity.setUrl(shortUrl);
            shortUrlEntity.setHash(hash);

            shortUrlDAO.saveShortUrl(shortUrlEntity);

        } else {
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        // Cache Logic
        shortUrlRedisRepository.save(shortUrlResponseDto);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        if (originalUrl.contains("me2.do")) {
            throw new RuntimeException();
        }

        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
            originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        // Cache Logic
        shortUrlRedisRepository.save(shortUrlResponseDto);

        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;


    }

    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if (url.contains("me2.do")) {//url이 숏형식일경우
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl' : {}" ,url);
            deleteByShortUrl(url);
        } else {//url이 오리지날형식일경우
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl' : {}" ,url);
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }

    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/util/shorturl")
            .queryParam("url", originalUrl)
            .encode()
            .build()
            .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
            entity, NaverUriDto.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }

}