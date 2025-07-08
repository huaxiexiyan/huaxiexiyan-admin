package com.huaxiexiyan.erp.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

/**
 * @author xiyan
 * @date 2025/7/7 16:16
 */
public class CatIdKeyGenerator implements IKeyGenerator {

	@Override
	public String executeSql(String incrementerName) {
		return "select nextval('" + incrementerName + "')";
	}

	@Override
	public DbType dbType() {
		return DbType.POSTGRE_SQL;
	}

}
