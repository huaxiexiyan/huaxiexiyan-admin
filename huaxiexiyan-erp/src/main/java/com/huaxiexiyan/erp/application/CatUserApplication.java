package com.huaxiexiyan.erp.application;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huaxiexiyan.erp.domain.CatUser;
import com.huaxiexiyan.erp.infrastructure.repository.mapper.CatUserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiyan
 * @date 2025/7/2 19:31
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class CatUserApplication {

	private final CatUserMapper catUserRepository;

	public CatUser getByUsername(String username) {
		return catUserRepository.selectOne(Wrappers.<CatUser>lambdaQuery()
			.eq(CatUser::getUsername, username));
	}

}
