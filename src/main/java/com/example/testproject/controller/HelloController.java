package com.example.testproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //이클래스가 컨트롤러로 사용된다를 알림
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class); //로거받을 해당클래스를 넣음

    //@RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    @GetMapping("hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("log-test")
    public void logTest(){
        LOGGER.trace("Trace log");
        LOGGER.debug("Debug log");
        LOGGER.info("Info log");
        LOGGER.warn("Warn log");
        LOGGER.error("Error log");
    }

    @PostMapping("/exception")
    public void exceptionTest() throws Exception{
        throw new Exception();
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String,String>> ExcceptionHandler(Exception e){
//        HttpHeaders responseHeaders = new HttpHeaders();
//
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//        LOGGER.info("Contoller 내 ExceptionHandler 호출");
//        LOGGER.info(e.getLocalizedMessage());
//
//        Map<String,String> map = new HashMap<>();
//        map.put("error type",httpStatus.getReasonPhrase());
//        map.put("code","400");
//        map.put("message","에러 발생");
//
//        return new ResponseEntity<>(map,responseHeaders,httpStatus);
//    }
}
