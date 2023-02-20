package com.example.testproject;

import com.example.testproject.config.ProfileManager;
import com.example.testproject.config.env.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaAuditing //baseentry의 어노테이션 EntityListeners을 동작하게함.
@SpringBootApplication
public class TestprojectApplication {

	private Logger LOGGER = LoggerFactory.getLogger(TestprojectApplication.class);

//	public TestprojectApplication(EnvConfiguration envConfiguration, ProfileManager profileManager){
//		LOGGER.info(envConfiguration.getMessage());
//		profileManager.printActiveProfiles();
//	}
	public static void main(String[] args) {
		SpringApplication.run(TestprojectApplication.class, args);
	}

}
