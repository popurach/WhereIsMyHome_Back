package com.whereismy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.whereismy.vo.Notice;

@Mapper
public interface NoticeMapper {
	ArrayList<Notice> list();
	ArrayList<Notice> selectByUser(String user);
	ArrayList<Notice> selectByTitle(String title);
	int insert(Notice notice);
	int delete(String number);
	int update(Notice notice);
	int deleteAll();
}
