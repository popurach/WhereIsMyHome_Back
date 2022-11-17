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
public class QnA {
    @ApiModelProperty(value="게시글 번호")
    String num;
    @ApiModelProperty(value="작성자")
    String writer;
    @ApiModelProperty(value="게시글 제목")
    String title;
    @ApiModelProperty(value="게시글 내용")
    String content;
    @ApiModelProperty(value="게시 날짜")
    String date;
}
