package com.huaxiexiyan.erp.presentation.config;

import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.erp.infrastructure.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author xiyan
 * @date 2025/7/3 10:59
 */
@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
		log.error("Exception: {}", ex);
		String code = ex.getErrorCode();
		String devMessage = ex.getMessage();
		Integer showType = ex.getShowType();
		ApiResponse<Void> fail = ApiResponse.fail(code, devMessage, showType);
		this.addHost(fail);
		return ResponseEntity.ok(fail);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
		log.error("Exception: {}", ex);
		// 通用异常处理
		String code = "500";
		String devMessage = ex.getMessage();
		ApiResponse<Void> fail = ApiResponse.fail(code, devMessage, 1);
		this.addHost(fail);
		return ResponseEntity.internalServerError().body(fail);
	}

	private void addHost(ApiResponse<Void> fail) {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (Objects.isNull(attrs)) {
			fail.setHost("Unknown");
			return;
		}
		HttpServletRequest request = attrs.getRequest();
		fail.setHost(request.getHeader("Host"));
	}

}

