package com.whereismy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
	@ApiModelProperty(value="번호")
	private int num;
    @ApiModelProperty(value="사용자 id")
    private String userId;
//    @ApiModelProperty(value="관심 지역 dongCode")
//    String dongCode;
    @ApiModelProperty(value="관심 지역 시도")
	private String sidoName;
	@ApiModelProperty(value="관심 지역 구군")
	private String gugunName;
	@ApiModelProperty(value="관심 지역 동")
	private String dongName;
}
