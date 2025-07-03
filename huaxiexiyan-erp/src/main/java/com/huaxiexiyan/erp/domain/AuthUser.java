package com.huaxiexiyan.erp.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author xiyan
 * @date 2025/7/3 11:58
 */
@Data
public class AuthUser {

	@JsonDeserialize(as = Long.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

}
