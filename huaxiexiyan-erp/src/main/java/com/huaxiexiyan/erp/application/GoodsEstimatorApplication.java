package com.huaxiexiyan.erp.application;

import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.domain.PDDGoodsEstimator;
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

	/**
	 * 根据 进价 快递费 折扣 净利润 -> 售价
	 *
	 * @return
	 */
	public PDDGoodsEstimator calculate(AuthUser authUser, PDDGoodsEstimator pddGoodsEstimator) {
		// 计算售价
		pddGoodsEstimator.calculateSellingPrice();
		// 计算标记
		pddGoodsEstimator.calculateListPrice();
		// 计算毛利与净利润相关
		pddGoodsEstimator.calculateGrossProfit();
		pddGoodsEstimator.calculateGrossMarginRate();
		pddGoodsEstimator.calculateNetProfit();
		pddGoodsEstimator.calculateNetMarginRate();
		// 计算投产比
		pddGoodsEstimator.calculateRoi();
		return pddGoodsEstimator;
	}

}
