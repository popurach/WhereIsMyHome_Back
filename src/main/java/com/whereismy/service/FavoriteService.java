package com.whereismy.service;


import com.whereismy.vo.BaseAddress;
import com.whereismy.vo.Favorite;
import com.whereismy.vo.FavoriteAddress;
import com.whereismy.vo.HomeDeal;

import java.util.ArrayList;

public interface FavoriteService {
	//관심 지역 추가
	void insert(Favorite favorite);
	//관심 지역 제거
	void delete(int num);
	//관심 지역 매물 검색
	ArrayList<HomeDeal> search(FavoriteAddress favorite);
	//사용자가 선택한 관심 지역 리스트 조회
	ArrayList<Favorite> addressName(String id);
}
