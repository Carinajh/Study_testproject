package com.example.testproject.config;

import com.ulisesbocchio.jasyptspringboot.encryptor.PooledStringEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasypyConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){
        String password ="testspring";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);//암호화키
        config.setAlgorithm("PBEWithMD5AndDES");//암호화 알고리즘
        config.setKeyObtentionIterations("1000");//반복해싱횟수
        config.setPoolSize("1");//인스턴스 풀
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");//salt생성 클래스
        config.setStringOutputType("base64");//인코딩방식

        encryptor.setConfig(config);
        return  encryptor;
    }
}
