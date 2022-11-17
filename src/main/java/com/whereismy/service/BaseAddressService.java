package com.whereismy.service;

import java.util.ArrayList;

import com.whereismy.vo.HomeDeal;
import com.whereismy.vo.Search;

public interface BaseAddressService {

	ArrayList<String> getSidoList();

	ArrayList<String> getGunguList(String sido);

	ArrayList<String> getDongList(HomeDeal hd);

	ArrayList<HomeDeal> getInfo(HomeDeal hd);

	ArrayList<HomeDeal> getSearch(HomeDeal hd);


}
