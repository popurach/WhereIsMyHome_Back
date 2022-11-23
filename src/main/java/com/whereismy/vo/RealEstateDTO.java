package com.whereismy.vo;

public class RealEstateDTO {
	private String ImgUrl;
	private String NewsUrl;
	private String Title;
	private String Preview;
	
	public RealEstateDTO() {}
	
	public RealEstateDTO(String imgUrl, String newsUrl, String title, String preview) {
		super();
		ImgUrl = imgUrl;
		NewsUrl = newsUrl;
		Title = title;
		Preview = preview;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	public String getNewsUrl() {
		return NewsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		NewsUrl = newsUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPreview() {
		return Preview;
	}

	public void setPreview(String preview) {
		Preview = preview;
	}
	
	
}
