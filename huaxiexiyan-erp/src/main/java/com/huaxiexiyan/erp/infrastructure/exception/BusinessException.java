package com.huaxiexiyan.erp.infrastructure.exception;

import lombok.Getter;

/**
 * @author xiyan
 * @date 2025/7/3 11:03
 */
@Getter
public class BusinessException extends RuntimeException {


	private final String errorCode;

	private final Integer showType;

	public BusinessException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.showType = 1;
	}


	public BusinessException(String errorCode, String errorMessage, Integer showType) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.showType = showType;
	}


}
