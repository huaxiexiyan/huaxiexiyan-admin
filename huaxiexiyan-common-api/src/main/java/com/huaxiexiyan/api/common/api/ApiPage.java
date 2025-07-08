package com.huaxiexiyan.api.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author xiyan
 * @date 2022-04-03 13:55
 */
@Data
public class ApiPage<T> {

	private List<T> records;

	private long current = 1;

	private long size = 10;

	private long total = 0;

	@JsonIgnore
	public IPage<T> getIpage() {
		return new Page<T>(current, size, total);
	}

}
