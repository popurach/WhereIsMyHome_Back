package com.whereismy.service;

import com.whereismy.mapper.QnAMapper;
import com.whereismy.vo.Comment;
import com.whereismy.vo.QnA;
import com.whereismy.vo.QnAPost;
import com.whereismy.vo.QnAUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnAServiceImpl implements QnAService{
	@Autowired
	QnAMapper mapper;

	@Override
	public ArrayList<QnA> getQnAList() {
		return mapper.getQnAList();
	}

	@Override
	public QnA getOneQnA(String num) {
		return mapper.getOneQnA(num);
	}

	@Override
	public void insertQnA(QnAPost qna) {
		mapper.insertQnA(qna);
	}

	@Override
	public void updateQnA(String num, QnAUpdate qna) {
		mapper.updateQnA(num, qna);
	}

	@Override
	public void deleteQnA(String num) {
		mapper.deleteQnA(num);
	}

	@Override
	public ArrayList<QnA> searchQnA(String title) {
		return mapper.searchQnA(title);
	}

	@Override
	public ArrayList<Comment> getComment(String bnum) {
		return mapper.getComment(bnum);
	}

	@Override
	public void insertComment(Comment comment) {
		mapper.insertComment(comment);
	}

	@Override
	public void deleteComment(String bnum, String num) {
		mapper.deleteComment(bnum,num);
	}
}
