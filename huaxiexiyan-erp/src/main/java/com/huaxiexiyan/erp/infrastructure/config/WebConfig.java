package com.huaxiexiyan.erp.infrastructure.config;

import com.huaxiexiyan.erp.presentation.interceptor.AuthInterceptor;
import com.huaxiexiyan.erp.presentation.interceptor.AuthUserArgumentResolver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/17 15:01
 */
@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LogInterceptor logInterceptor;

	private final AuthInterceptor authInterceptor;

	private final AuthUserArgumentResolver authUserArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authUserArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);

		registry.addInterceptor(authInterceptor)
			.addPathPatterns("/**") // 你要保护的路径
			.excludePathPatterns("/auth/login"); // 排除不需要 token 的路径
	}
}
