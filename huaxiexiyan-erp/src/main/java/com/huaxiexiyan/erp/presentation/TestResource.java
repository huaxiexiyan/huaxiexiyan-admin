package com.huaxiexiyan.erp.presentation;


import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.presentation.interceptor.LoginUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2024/1/8 15:35
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/test")
@RestController
public class TestResource {

	@GetMapping("/demo1")
	public ApiResponse<AuthUser> test1(@LoginUser AuthUser authUser) {
		return ApiResponse.ok(authUser);
	}


}

