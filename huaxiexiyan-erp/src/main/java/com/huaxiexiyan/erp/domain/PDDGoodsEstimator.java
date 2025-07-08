package com.huaxiexiyan.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huaxiexiyan.api.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 拼多多商品估算
 *
 * @author xiyan
 * @date 2025/7/5 16:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pdd_goods_estimator")
public class PDDGoodsEstimator extends AbstractEntity {

	// 商品名称
	private String goodsName;
	// sku名称
	private String skuName;
	// 商品进价
	private BigDecimal purchaseCost;
	// 快递费
	private BigDecimal deliveryCost;
	// 包装费
	private BigDecimal packagingCost;
	// 推流费用
	private BigDecimal promotionCost;
	// 活动折扣
	private BigDecimal discountRate;
	// 商品售价 (商品进价+快递费+包装费 / (1-折扣))
	private BigDecimal sellingPrice;
	// 商品标价
	private BigDecimal listPrice;
	// 销售毛利率 (商品进价+快递费+包装费+推流费用)/商品售价)
	private BigDecimal grossMarginRate;
	// 商品毛利率 (商品进价/商品售价)
	private BigDecimal netMarginRate;
	// 销售利润
	private BigDecimal grossProfit;
	// 最终利润
	private BigDecimal netProfit;
	// 投产比
	private BigDecimal roi;
	// 利润投产比
	private BigDecimal netProfitRoi;

	// 计算毛利润
	@JsonIgnore
	public void calculateGrossProfit() {
		this.grossProfit = sellingPrice.subtract(this.purchaseCost);
	}

	// 计算毛利润率
	@JsonIgnore
	public void calculateGrossMarginRate() {
		this.grossMarginRate = this.grossProfit.divide(this.sellingPrice, 4, RoundingMode.HALF_UP)
			.multiply(new BigDecimal("100"))
			.setScale(2, RoundingMode.HALF_UP);
	}

	// 计算净利润
	@JsonIgnore
	public void calculateNetProfit() {
		this.netProfit = this.sellingPrice.subtract(this.purchaseCost)
			.subtract(this.deliveryCost)
			.subtract(this.packagingCost)
			.subtract(this.promotionCost);
	}

	@JsonIgnore
	public void calculateNetProfitByNetMarginRate() {
		this.netProfit = this.sellingPrice.multiply(this.netMarginRate
			.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
	}

	// 计算净利润率
	@JsonIgnore
	public void calculateNetMarginRate() {
		this.netMarginRate = this.netProfit.divide(this.sellingPrice, 4, RoundingMode.HALF_UP)
			.multiply(new BigDecimal("100"))
			.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * 计算售价
	 * <p>
	 * 售价 = 商品进价 + 快递费 + 包装费 + 推流费用 + 净利润
	 */
	@JsonIgnore
	public void calculateSellingPrice() {
		this.sellingPrice = this.purchaseCost.add(this.deliveryCost)
			.add(this.packagingCost)
			.add(this.promotionCost)
			.add(this.netProfit);
	}

	public void calculateSellingPriceByGrossMarginRate() {
		this.sellingPrice = this.purchaseCost.divide((BigDecimal.ONE
				.subtract(this.grossMarginRate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP))),
			2, RoundingMode.HALF_UP);
	}

	public void calculatePromotionCost() {
		this.promotionCost = this.sellingPrice.subtract(this.purchaseCost)
			.subtract(this.deliveryCost)
			.subtract(this.packagingCost)
			.subtract(this.netProfit);
	}

	/**
	 * 计算标价
	 * 售价 = 标价 * 折扣率
	 * 标价 = 售价 / 折扣率
	 */
	@JsonIgnore
	public void calculateListPrice() {
		this.listPrice = this.sellingPrice
			.divide(this.discountRate.divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP), 2, RoundingMode.HALF_UP);
	}


	/**
	 * 计算投产比
	 * <p>
	 * 投产比 = 售价 / 推流费用
	 */
	@JsonIgnore
	public void calculateRoi() {
		this.roi = this.sellingPrice.divide(this.promotionCost, 2, RoundingMode.HALF_UP);
	}

	@JsonIgnore
	public void calculateNetProfitRoi() {
		this.netProfitRoi = this.netProfit.divide(this.promotionCost, 2, RoundingMode.HALF_UP);
	}


	/**
	 * 计算推流费用 = 售价 / 投产比
	 */
	@JsonIgnore
	public void calculatePromotionCostByRoi() {
		this.promotionCost = this.sellingPrice.divide(this.roi, 2, RoundingMode.HALF_UP);
	}



}
