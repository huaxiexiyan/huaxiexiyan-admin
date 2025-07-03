package com.huaxiexiyan.erp;

import com.huaxiexiyan.erp.infrastructure.config.JwtProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.huaxiexiyan.erp.infrastructure.repository.mapper")
@EnableConfigurationProperties(JwtProperties.class)
public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

}
