package com.huaxiexiyan.erp.application;

import com.huaxiexiyan.api.common.type.CatTreeNode;
import com.huaxiexiyan.erp.domain.CatMenu;
import com.huaxiexiyan.erp.infrastructure.repository.mapper.CatMenuMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2025/7/2 19:31
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class CatMenuApplication {

	private final CatMenuMapper catMenuMapper;

	public List<CatMenu> treeAuthMenuByUserId(Long userId) {
		List<CatMenu> catMenus = catMenuMapper.selectAuthMenuByUserId(userId);
		// 形成树形
		List<CatMenu> tree = CatTreeNode.merge(catMenus);
		return tree;
	}

}
