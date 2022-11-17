package com.whereismy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.whereismy.service.BaseAddressService;
import com.whereismy.vo.HomeDeal;
import com.whereismy.vo.Search;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@Api(tags = {"BaseAddress Controller API"}, description = "BaseAddress 관련 서비스")
public class BaseAddressController {
	@Autowired
	BaseAddressService service;
	
	@GetMapping(value = "/getSidolist")
	public ArrayList<String> getSidolist() {
		return service.getSidoList();
	}
	
	@GetMapping(value = "/getGunguList/{sido}")
	public ArrayList<String> getGungulist(@PathVariable String sido) {
		return service.getGunguList(sido);
	}
	
	@PostMapping(value = "/getDongList")
	public ArrayList<String> getDongList(@RequestBody HomeDeal hd){
		return service.getDongList(hd);
	}
	
	@PostMapping(value = "/getInfo")
	public ArrayList<HomeDeal> getInfo(@RequestBody HomeDeal hd){
		return service.getInfo(hd);
	}
	
	@PostMapping(value = "/getSearch")
	public ArrayList<HomeDeal> getSearch(@RequestBody HomeDeal hd){
		return service.getSearch(hd);
	}
	
}
