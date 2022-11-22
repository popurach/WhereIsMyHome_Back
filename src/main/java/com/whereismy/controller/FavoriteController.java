package com.whereismy.controller;

import com.whereismy.service.FavoriteService;
import com.whereismy.vo.BaseAddress;
import com.whereismy.vo.Favorite;
import com.whereismy.vo.FavoriteAddress;
import com.whereismy.vo.HomeDeal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@Api(tags = {"Favorite Controller API"}, description = "관심 지역 관련 서비스")
public class FavoriteController {
    @Autowired
    FavoriteService service;

    //관심 지역 추가
    @PostMapping("/favorite")
    public void insert(@RequestBody Favorite favorite){
        service.insert(favorite);
    }

    //관심 지역 제거
    @DeleteMapping("/favorite")
    public void delete(@RequestBody Favorite favorite){
        service.delete(favorite);
    }

    //관심지역 매물 검색
    @PostMapping("/favorite/search")
    public ArrayList<HomeDeal> search(@RequestBody FavoriteAddress favorite){
        return service.search(favorite);
    }

    //사용자별 관심 지역 리스트 출력
    @GetMapping("/favorite/{id}")
    public ArrayList<Favorite> addressName(@PathVariable String id){
        return service.addressName(id);
    }
}
