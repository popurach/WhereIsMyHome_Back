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
public class QnAUpdate {
    @ApiModelProperty(value="게시글 제목")
    String title;
    @ApiModelProperty(value="게시글 내용")
    String content;
}
