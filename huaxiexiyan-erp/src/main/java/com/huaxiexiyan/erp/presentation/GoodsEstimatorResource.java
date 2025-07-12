package com.huaxiexiyan.erp.presentation;

import com.huaxiexiyan.api.common.api.ApiPage;
import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.erp.application.GoodsEstimatorApplication;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.domain.PDDGoodsEstimator;
import com.huaxiexiyan.erp.presentation.interceptor.LoginUser;
import com.huaxiexiyan.erp.presentation.query.PDDGoodsEstimatorQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiyan
 * @date 2025/7/6 16:54
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/goods-estimator")
@RestController
public class GoodsEstimatorResource {

	private final GoodsEstimatorApplication goodsEstimatorApplication;

	@PostMapping("/pdd/calculate/price-by-roi")
	public ApiResponse<PDDGoodsEstimator> calculatePriceByRoi(@LoginUser AuthUser authUser,
															  @RequestBody PDDGoodsEstimator pddGoodsEstimator) {
		PDDGoodsEstimator calculate = goodsEstimatorApplication.calculatePriceByRoi(authUser, pddGoodsEstimator);
		return ApiResponse.ok(calculate);
	}

	// @PostMapping("/pdd/calculate/price-by-net-profit")
	// public ApiResponse<PDDGoodsEstimator> calculatePriceByNetProfit(@LoginUser AuthUser authUser,
	// 																@RequestBody PDDGoodsEstimator pddGoodsEstimator) {
	// 	PDDGoodsEstimator calculate = goodsEstimatorApplication.calculatePriceByNetProfit(authUser, pddGoodsEstimator);
	// 	return ApiResponse.ok(calculate);
	// }
	//
	// @PostMapping("/pdd/calculate/price-by-net-margin")
	// public ApiResponse<PDDGoodsEstimator> calculatePriceByNetMargin(@LoginUser AuthUser authUser,
	// 																@RequestBody PDDGoodsEstimator pddGoodsEstimator) {
	// 	PDDGoodsEstimator calculate = goodsEstimatorApplication.calculatePriceByNetMargin(authUser, pddGoodsEstimator);
	// 	return ApiResponse.ok(calculate);
	// }


	@GetMapping("/pdd/history")
	public ApiResponse<ApiPage<PDDGoodsEstimator>> history(@LoginUser AuthUser authUser,
														   @ModelAttribute PDDGoodsEstimatorQuery pddGoodsEstimatorQuery) {
		ApiPage<PDDGoodsEstimator> apiPage = goodsEstimatorApplication.listPddGoodsEstimatorHistory(authUser, pddGoodsEstimatorQuery);
		return ApiResponse.ok(apiPage);
	}

	@PostMapping("/pdd/history")
	public ApiResponse<Void> saveCalculateHistory(@LoginUser AuthUser authUser, @RequestBody PDDGoodsEstimator pddGoodsEstimator) {
		goodsEstimatorApplication.saveCalculateHistory(authUser, pddGoodsEstimator);
		return ApiResponse.ok();
	}

	@DeleteMapping("/pdd/history/{id}")
	public ApiResponse<Void> removeCalculateHistory(@LoginUser AuthUser authUser, @PathVariable Long id) {
		goodsEstimatorApplication.removeCalculateHistory(authUser, id);
		return ApiResponse.ok();
	}

}
