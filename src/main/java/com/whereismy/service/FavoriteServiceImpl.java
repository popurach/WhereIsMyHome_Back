package com.whereismy.service;

import com.whereismy.mapper.FavoriteMapper;
import com.whereismy.vo.BaseAddress;
import com.whereismy.vo.Favorite;
import com.whereismy.vo.FavoriteAddress;
import com.whereismy.vo.HomeDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    FavoriteMapper mapper;

    @Override
    public void insert(Favorite favorite) {
        mapper.insert(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        mapper.delete(favorite);
    }

    @Override
    public ArrayList<HomeDeal> search(FavoriteAddress favorite) {
        return mapper.search(favorite);
    }

    @Override
    public ArrayList<BaseAddress> addressName(String id) {
        return mapper.addressName(id);
    }
}
