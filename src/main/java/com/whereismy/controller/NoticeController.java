package com.whereismy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.whereismy.service.NoticeService;
import com.whereismy.vo.Notice;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@Api
@RestController
public class NoticeController {
	@Autowired
	NoticeService service;
	
	@GetMapping(value = "/Notice")
	public ArrayList<Notice> list(){
		return service.list();
	} 
	
	@GetMapping(value = "/selectByUser/{user}")
	public ArrayList<Notice> selectByUser(@PathVariable String user) {
		return service.selectByUser(user);
	}
	
	@GetMapping(value = "/selectByTitle/{title}")
	public ArrayList<Notice> selectByTitle(@PathVariable String title) {
		return service.selectByTitle(title);
	}
	
	@PostMapping(value = "/Notice")
	public int insert(@RequestBody Notice notice) {
		return service.insert(notice);
	}
	
	@DeleteMapping(value="/Notice/{num}")
	public int delete(@PathVariable String number) {
		return service.delete(number);
	}
	
	@PutMapping(value = "/Notice")
	public int update(@RequestBody Notice notice) {
		return service.update(notice);
	}
	
	@DeleteMapping(value = "/Notice")
	public int delete() {
		return service.deleteAll();
	}
}
