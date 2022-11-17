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
    @ApiModelProperty(value="사용자 id")
    String userId;
    @ApiModelProperty(value="관심 지역 dongCode")
    String dongCode;
}
