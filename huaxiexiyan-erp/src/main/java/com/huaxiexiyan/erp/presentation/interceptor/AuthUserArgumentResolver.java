package com.huaxiexiyan.erp.presentation.interceptor;

import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.infrastructure.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author xiyan
 * @date 2025/7/3 12:10
 */
@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginUser.class)
			&& parameter.getParameterType().equals(AuthUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		Object loginUser = request.getAttribute("authUser");

		if (loginUser == null) {
			throw new BusinessException("10002", "未登录或 Token 无效");
		}

		return loginUser;
	}
}
