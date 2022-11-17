package com.whereismy.service;

import java.util.ArrayList;

import com.whereismy.vo.Notice;

public interface NoticeService {
	ArrayList<Notice> list();
	ArrayList<Notice> selectByUser(String user);
	ArrayList<Notice> selectByTitle(String user);
	int insert(Notice notice);
	int delete(String number);
	int update(Notice notice);
	int deleteAll();
}
