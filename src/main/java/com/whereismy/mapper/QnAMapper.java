package com.whereismy.mapper;

import com.whereismy.vo.Comment;
import com.whereismy.vo.QnA;
import com.whereismy.vo.QnAPost;
import com.whereismy.vo.QnAUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface QnAMapper {
	ArrayList<QnA> getQnAList();
	QnA getOneQnA(String num);
	void insertQnA(QnAPost qna);
	void updateQnA(@Param("num") String num, @Param("qna") QnAUpdate qna);
	void deleteQnA(String num);
	ArrayList<QnA> searchQnA(String title);

	//댓글
	ArrayList<Comment> getComment(String bnum);
	void insertComment(Comment comment);
	void deleteComment(@Param("bnum") String bnum, @Param("num") String num);
}
