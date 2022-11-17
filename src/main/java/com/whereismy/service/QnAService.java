package com.whereismy.service;

import com.whereismy.vo.Comment;
import com.whereismy.vo.QnA;
import com.whereismy.vo.QnAPost;
import com.whereismy.vo.QnAUpdate;

import java.util.ArrayList;

public interface QnAService {
	//게시글
	ArrayList<QnA> getQnAList();
	QnA getOneQnA(String num);
	void insertQnA(QnAPost qna);
	void updateQnA(String num, QnAUpdate qna);
	void deleteQnA(String num);
	ArrayList<QnA> searchQnA(String title);

	//댓글
	ArrayList<Comment> getComment(String bnum);
	void insertComment(Comment comment);
	void deleteComment(String bnum, String num);
}
