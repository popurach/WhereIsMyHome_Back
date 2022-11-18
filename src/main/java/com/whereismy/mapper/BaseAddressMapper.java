package com.whereismy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.whereismy.vo.HomeDeal;
import com.whereismy.vo.Search;

@Mapper
public interface BaseAddressMapper {

	ArrayList<String> getSidoList();

	ArrayList<String> getGunguList(String sido);

	ArrayList<String> getDongList(HomeDeal hd);

	ArrayList<HomeDeal> getInfo(HomeDeal hd);

	ArrayList<HomeDeal> showFavorite(ArrayList<String> fav);

	ArrayList<HomeDeal> getSearch(HomeDeal hd);

}