package com.huaxiexiyan.erp.presentation;


import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.api.common.utility.JSONUtils;
import com.huaxiexiyan.erp.application.CatMenuApplication;
import com.huaxiexiyan.erp.application.CatUserApplication;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.domain.CatMenu;
import com.huaxiexiyan.erp.domain.CatUser;
import com.huaxiexiyan.erp.infrastructure.exception.BusinessException;
import com.huaxiexiyan.erp.infrastructure.util.JwtUtil;
import com.huaxiexiyan.erp.presentation.interceptor.LoginUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

	private final PasswordEncoder bCryptPasswordEncoder;

	private final CatUserApplication catUserApplication;

	private final CatMenuApplication catMenuApplication;

	@PostMapping("/login")
	public ApiResponse<Map<String, String>> login(@RequestBody CatUser requestCatUser) {
		CatUser catUser = catUserApplication.getByUsername(requestCatUser.getUsername());
		if (Objects.isNull(catUser)) {
			// 用户名或密码错误
			throw new BusinessException("10001", "用户名或密码错误");
		}

		boolean matches = bCryptPasswordEncoder.matches(requestCatUser.getPassword(), catUser.getPassword());
		if (!matches) {
			// 用户名或密码错误
			throw new BusinessException("10001", "用户名或密码错误");
		}

		Map<String, Object> subjectMap = new HashMap<>();
		subjectMap.put("id", catUser.getId());
		subjectMap.put("username", catUser.getUsername());
		subjectMap.put("nickname", catUser.getNickname());
		String subject = JSONUtils.toJsonStr(subjectMap);
		String token = jwtUtil.generateToken(subject);
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		response.put("username", catUser.getUsername());
		response.put("nickname", catUser.getNickname());
		return ApiResponse.ok(response);
	}

	@GetMapping("/menu")
	public ApiResponse<List<CatMenu>> authMenu(@LoginUser AuthUser authUser) {
		List<CatMenu> menuTree = catMenuApplication.treeAuthMenuByUserId(authUser.getId());
		return ApiResponse.ok(menuTree);
	}

}

