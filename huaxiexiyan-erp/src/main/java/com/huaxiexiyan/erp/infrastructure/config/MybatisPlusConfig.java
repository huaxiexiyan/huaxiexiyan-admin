package com.huaxiexiyan.erp.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.huaxiexiyan.erp.infrastructure.idgeneration.strategy.CatIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiyan
 * @date 2022/10/14 15:43
 */
@AllArgsConstructor
@Configuration
public class MybatisPlusConfig {

	private final CatIdGenerator catIdGenerator;

	/**
	 * 新的分页插件、新多租户插件配置
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
		return interceptor;
	}

	// @Bean
	// public IdentifierGenerator idGenerator() {
	// 	return entity -> {
	// 		// 使用实体类名作为业务键
	// 		// String bizKey = entity.getClass().getName();
	// 		// 根据业务键调用分布式ID生成服务
	// 		// 调用分布式ID生成逻辑
	// 		return catIdGenerator.nextId();
	// 	};
	// }


}
