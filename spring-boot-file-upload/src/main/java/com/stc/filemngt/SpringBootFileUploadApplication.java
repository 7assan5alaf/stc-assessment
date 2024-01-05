package com.stc.filemngt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.stc.filemngt.entity.FileStorage;


@SpringBootApplication
@EnableConfigurationProperties({FileStorage.class})
public class SpringBootFileUploadApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootFileUploadApplication.class, args);
	}

}
