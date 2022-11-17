package com.whereismy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HomeDeal {
	@ApiModelProperty(value="시도")
	private String sidoName;
	@ApiModelProperty(value="구군")
	private String gugunName;
	@ApiModelProperty(value="동")
	private String dongName;
	@ApiModelProperty(value="아파트 이름")
	private String aptName;
	@ApiModelProperty(value="거래 년도")
	private String dealYear;
	@ApiModelProperty(value="거래 금액")
	private String dealAmount;
	
	public HomeDeal() {}

	public HomeDeal(String sidoName, String gugunName, String dongName, String aptName, String dealYear,String dealAmount) {
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.aptName = aptName;
		this.dealYear=dealYear;
		this.dealAmount = dealAmount;
	}
	
	
	@Override
	public String toString() {
		return "HomeDeal [sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + ", aptName="
				+ aptName + ", dealYear=" + dealYear + ", dealAmount=" + dealAmount + "]";
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	

}
