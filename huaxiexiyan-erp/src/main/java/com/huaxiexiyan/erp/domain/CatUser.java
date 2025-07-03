package com.huaxiexiyan.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxiexiyan.api.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户类
 *
 * @author xiyan
 * @date 2025/7/1 15:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class CatUser extends AbstractEntity {

	private String username;

	private String password;

	private String nickname;

}
