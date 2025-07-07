package com.huaxiexiyan.erp.presentation.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.huaxiexiyan.api.common.utility.JSONUtils;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.infrastructure.exception.BusinessException;
import com.huaxiexiyan.erp.infrastructure.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * @author xiyan
 * @date 2025/7/3 11:59
 */
@Slf4j
@AllArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private final JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = request.getHeader("Authorization");

		if (token == null || !token.startsWith("Bearer ")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new BusinessException("10003", "缺失token");
		}

		token = token.substring(7); // 去掉 Bearer 前缀
		DecodedJWT claims = jwtUtil.verifyToken(token);

		if (Objects.isNull(claims) || Objects.isNull(claims.getSubject())) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new BusinessException("10003", "无效token信息");
		}
		String subject = claims.getSubject();
		AuthUser authUser = JSONUtils.parse(subject, AuthUser.class);
		// 可以把用户信息保存到 request attribute 中传递
		request.setAttribute("authUser", authUser);
		return true;
	}

}

