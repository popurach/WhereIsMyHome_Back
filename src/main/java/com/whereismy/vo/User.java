package com.whereismy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
	@ApiModelProperty(value="user id")
	private String id;
	@ApiModelProperty(value="user 비밀번호")
	private String pass;
	@ApiModelProperty(value="user 이름")
	private String name;
	@ApiModelProperty(value="user 주소")
	private String address;
	@ApiModelProperty(value="user 전화번호")
	private String tel;
	
	public User() {	}
	
	public User(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public User(String id, String name, String address, String tel) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}
	
	public User(String id, String pass, String name, String address, String tel) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	

	
	
}
