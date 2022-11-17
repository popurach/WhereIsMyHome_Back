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
public class Comment {
    @ApiModelProperty(value="댓글 번호")
    String num;
    @ApiModelProperty(value="게시글 번호")
    String bnum;
    @ApiModelProperty(value="작성자")
    String writer;
    @ApiModelProperty(value="댓글 내용")
    String comment;
}
