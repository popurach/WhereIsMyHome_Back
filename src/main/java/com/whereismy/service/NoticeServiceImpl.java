package com.whereismy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismy.mapper.NoticeMapper;
import com.whereismy.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	NoticeMapper mapper;
	
	@Override
	public ArrayList<Notice> list() {
		return mapper.list();
	}

	@Override
	public ArrayList<Notice> selectByUser(String user) {
		return mapper.selectByUser(user);
	}


	@Override
	public ArrayList<Notice> selectByTitle(String user) {
		return mapper.selectByTitle(user);
	}
	
	@Override
	public int insert(Notice notice) {
		return mapper.insert(notice);
	}

	@Override
	public int delete(String number) {
		return mapper.delete(number);
	}

	@Override
	public int update(Notice notice) {
		return mapper.update(notice);
	}

	@Override
	public int deleteAll() {
		return mapper.deleteAll();
	}

}
