package com.whereismy.vo;

public class Search {
	private String dong, aptName;
	
	public Search() {
		
	}
	public Search(String dong, String aptName) {
		this.dong = dong;
		this.aptName = aptName;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	
}
