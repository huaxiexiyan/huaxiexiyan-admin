package com.huaxiexiyan.erp.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huaxiexiyan.api.common.api.ApiPage;
import com.huaxiexiyan.api.common.utility.IPageUtils;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.domain.PDDGoodsEstimator;
import com.huaxiexiyan.erp.infrastructure.repository.mapper.PDDGoodsEstimatorMapper;
import com.huaxiexiyan.erp.presentation.query.PDDGoodsEstimatorQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品评估器
 *
 * @author xiyan
 * @date 2025/7/5 11:31
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class GoodsEstimatorApplication {

	private final PDDGoodsEstimatorMapper pddGoodsEstimatorMapper;


	/**
	 * 以投产比定售价
	 * <p>
	 * 目标利润预期
	 * 成本 利润率 反推售价及投产
	 *
	 * @param authUser
	 * @param pddGoodsEstimator
	 * @return
	 */
	public PDDGoodsEstimator calculatePriceByRoi(AuthUser authUser, PDDGoodsEstimator pddGoodsEstimator) {
		pddGoodsEstimator.setId(null);
		pddGoodsEstimator.calculateSellingPriceByGrossMarginRate();
		pddGoodsEstimator.calculateNetProfitByNetMarginRate();
		pddGoodsEstimator.calculatePromotionCost();
		pddGoodsEstimator.calculateRoi();

		// 计算标价
		pddGoodsEstimator.calculateListPrice();
		// 计算毛利与净利润相关
		pddGoodsEstimator.calculateGrossProfit();
		// 计算投产比
		pddGoodsEstimator.calculateNetProfitRoi();

		// 存储计算历史
		pddGoodsEstimator.setCreatedBy(authUser.getId());
		pddGoodsEstimatorMapper.insert(pddGoodsEstimator);
		return pddGoodsEstimator;
	}

	public PDDGoodsEstimator calculatePriceByNetMargin(AuthUser authUser, PDDGoodsEstimator pddGoodsEstimator) {
		// 计算售价
		// pddGoodsEstimator.calculateSellingPrice();
		// // 计算标记
		// pddGoodsEstimator.calculateListPrice();
		// // 计算毛利与净利润相关
		// pddGoodsEstimator.calculateGrossProfit();
		// pddGoodsEstimator.calculateGrossMarginRate();
		// pddGoodsEstimator.calculateNetProfit();
		// pddGoodsEstimator.calculateNetMarginRate();
		// // 计算投产比
		// pddGoodsEstimator.calculateRoi();
		//
		// // 存储计算历史
		// pddGoodsEstimator.setCreatedBy(authUser.getId());
		// pddGoodsEstimatorMapper.insert(pddGoodsEstimator);
		return pddGoodsEstimator;
	}

	/**
	 * 根据 进价 快递费 折扣 净利润 -> 售价
	 *
	 * @return
	 */
	public PDDGoodsEstimator calculatePriceByNetProfit(AuthUser authUser, PDDGoodsEstimator pddGoodsEstimator) {
		// 计算售价
		// pddGoodsEstimator.calculateSellingPrice();
		// // 计算标记
		// pddGoodsEstimator.calculateListPrice();
		// // 计算毛利与净利润相关
		// pddGoodsEstimator.calculateGrossProfit();
		// pddGoodsEstimator.calculateGrossMarginRate();
		// pddGoodsEstimator.calculateNetProfit();
		// pddGoodsEstimator.calculateNetMarginRate();
		// // 计算投产比
		// pddGoodsEstimator.calculateRoi();
		//
		// // 存储计算历史
		// pddGoodsEstimator.setCreatedBy(authUser.getId());
		// pddGoodsEstimatorMapper.insert(pddGoodsEstimator);
		return pddGoodsEstimator;
	}

	public ApiPage<PDDGoodsEstimator> listPddGoodsEstimatorHistory(AuthUser authUser,
																   PDDGoodsEstimatorQuery pddGoodsEstimatorQuery) {
		IPage<PDDGoodsEstimator> page = pddGoodsEstimatorMapper.selectPage(pddGoodsEstimatorQuery.getIpage(),
			Wrappers.<PDDGoodsEstimator>lambdaQuery().orderByDesc(PDDGoodsEstimator::getCreatedTime));
		return IPageUtils.toApiPage(page, IPage::getRecords);
	}

}
