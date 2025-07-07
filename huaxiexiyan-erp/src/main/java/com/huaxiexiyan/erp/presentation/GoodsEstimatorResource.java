package com.huaxiexiyan.erp.presentation;

import com.huaxiexiyan.api.common.api.ApiResponse;
import com.huaxiexiyan.erp.application.GoodsEstimatorApplication;
import com.huaxiexiyan.erp.domain.AuthUser;
import com.huaxiexiyan.erp.domain.PDDGoodsEstimator;
import com.huaxiexiyan.erp.presentation.interceptor.LoginUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/pdd/calculate")
	public ApiResponse<PDDGoodsEstimator> calculate(@LoginUser AuthUser authUser, @RequestBody PDDGoodsEstimator pddGoodsEstimator) {
		PDDGoodsEstimator calculate = goodsEstimatorApplication.calculate(authUser, pddGoodsEstimator);
		return ApiResponse.ok(calculate);
	}

	@PostMapping("/pdd/history")
	public ApiResponse<PDDGoodsEstimator> history(@LoginUser AuthUser authUser, @RequestBody PDDGoodsEstimator pddGoodsEstimator) {
		PDDGoodsEstimator calculate = goodsEstimatorApplication.calculate(authUser, pddGoodsEstimator);
		return ApiResponse.ok(calculate);
	}

}
