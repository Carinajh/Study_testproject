package com.example.testproject.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Test
    void encryptTest(){
        String id = "root";
        String password = "1234";

        System.out.println(jasyptEnncoding(id));
        System.out.println(jasyptEnncoding(password));
    }

    public String jasyptEnncoding(String value){
        String key ="testspring";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("key");
        return encryptor.encrypt(value);
    }
}
