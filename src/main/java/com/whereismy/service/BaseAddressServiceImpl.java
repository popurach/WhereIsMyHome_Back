package com.whereismy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismy.mapper.BaseAddressMapper;
import com.whereismy.vo.HomeDeal;
import com.whereismy.vo.Search;
import org.springframework.stereotype.Service;

@Service
public class BaseAddressServiceImpl implements BaseAddressService{

	@Autowired
	BaseAddressMapper mapper;
	
	@Override
	public ArrayList<String> getSidoList() {
		return mapper.getSidoList();
	}

	@Override
	public ArrayList<String> getGunguList(String sido) {
		return mapper.getGunguList(sido);
	}

	@Override
	public ArrayList<String> getDongList(HomeDeal hd) {
		return mapper.getDongList(hd);
	}

	@Override
	public ArrayList<HomeDeal> getInfo(HomeDeal hd) {
		return mapper.getInfo(hd);
	}

	@Override
	public ArrayList<HomeDeal> getSearch(HomeDeal hd) {
		return mapper.getSearch(hd);
	}

}
