package com.huaxiexiyan.erp.infrastructure.idgeneration;


import com.huaxiexiyan.erp.infrastructure.idgeneration.strategy.CatIdGenerator;
import com.huaxiexiyan.erp.infrastructure.idgeneration.strategy.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xiyan
 * @date 2023/8/1 15:51
 */
@Configuration
public class CatIdConfig {

	@Bean
	public CatIdGenerator snowflake() {
		return new Snowflake(1, 1, true);
	}

}
