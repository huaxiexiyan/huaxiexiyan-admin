package com.huaxiexiyan.erp.presentation;


import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.api.common.type.Kv;
import com.huaxiexiyan.api.common.utility.JSONUtils;
import com.huaxiexiyan.erp.application.CatUserApplication;
import com.huaxiexiyan.erp.domain.CatUser;
import com.huaxiexiyan.erp.infrastructure.exception.BusinessException;
import com.huaxiexiyan.erp.infrastructure.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiyan
 * @date 2024/1/8 15:35
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthResource {

	private final JwtUtil jwtUtil;

	private final CatUserApplication catUserApplication;

	@PostMapping("/login")
	public ApiResponse<Kv<String, String>> login(@RequestBody CatUser requestCatUser) {
		CatUser catUser = catUserApplication.getByUsername(requestCatUser.getUsername());

		if (Objects.isNull(catUser)) {
			// 用户名或密码错误
			throw new BusinessException("10001", "用户名或密码错误");
		}

		Map<String, String> subjectMap = new HashMap<>();
		String subject = JSONUtils.toJsonStr(subjectMap);
		String token = jwtUtil.generateToken(subject);
		Kv<String, String> response = new Kv<>("token", token);
		return ApiResponse.ok(response);
	}

	@GetMapping("/token")
	public String token() {
		return jwtUtil.generateToken("test-user");
	}

	@GetMapping("/verify")
	public String verify(@RequestParam String token) {
		var decoded = jwtUtil.verifyToken(token);
		if (decoded == null) {
			return "Invalid token";
		}
		return "User: " + decoded.getSubject();
	}


}

