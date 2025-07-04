package com.huaxiexiyan.erp.infrastructure.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huaxiexiyan.erp.domain.CatMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * app实体表 Mapper 接口
 * </p>
 *
 * @author xiyan
 * @since 2023-10-22
 */
@Mapper
public interface CatMenuMapper extends BaseMapper<CatMenu> {

	List<CatMenu> selectAuthMenuByUserId(Long userId);

}
