package com.whereismy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ApiModel
public class ResetPass {
    @ApiModelProperty(value="user id")
    String id;
    @ApiModelProperty(value="새로운 비밀번호")
    String newPass;
    @ApiModelProperty(value="비밀번호 발급받을 이메일")
    String email;
}
