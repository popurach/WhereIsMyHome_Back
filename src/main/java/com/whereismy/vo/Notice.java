package com.whereismy.vo;

public class Notice {
	private int number;
	private String user;
	private String title;
	private String regdate;
	private String content;
	private int hit;
	
	public Notice() {
		
	}
	public Notice(int number, String user, String title, String regdate, String content, int hit) {
		super();
		this.number = number;
		this.user = user;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.hit = hit;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
