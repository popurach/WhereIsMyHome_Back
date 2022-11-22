package com.whereismy.mapper;


import com.whereismy.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FavoriteMapper {
	void insert(Favorite favorite);
	void delete(int num);
	ArrayList<HomeDeal> search(FavoriteAddress favorite);
	ArrayList<Favorite> addressName(String id);
}
