package com.whereismy.vo;

import java.util.List;

public class ResponseDTO {
	private List<RealEstateDTO> body;
	private String status;
	
	public ResponseDTO(){
		
	}

	public List<RealEstateDTO> getBody() {
		return body;
	}

	public void setBody(List<RealEstateDTO> body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
