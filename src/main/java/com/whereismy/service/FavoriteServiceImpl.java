package com.whereismy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismy.mapper.FavoriteMapper;
import com.whereismy.vo.Favorite;
import com.whereismy.vo.FavoriteAddress;
import com.whereismy.vo.HomeDeal;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    FavoriteMapper mapper;

    @Override
    public void insert(Favorite favorite) {
        mapper.insert(favorite);
    }

    @Override
    public void delete(int num) {
        mapper.delete(num);
    }

    @Override
    public ArrayList<HomeDeal> search(FavoriteAddress favorite) {
        return mapper.search(favorite);
    }

    @Override
    public ArrayList<Favorite> addressName(String id) {
        return mapper.addressName(id);
    }
}
