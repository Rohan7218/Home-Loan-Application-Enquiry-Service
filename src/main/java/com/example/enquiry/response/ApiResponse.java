package com.example.enquiry.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private String  msg;
	private Boolean status;
	private Object data;
	
	public ApiResponse(String msg) {
		super();
		this.msg = msg;
	}

	public ApiResponse(Boolean status) {
		super();
		this.status = status;
	}

	public ApiResponse(Object data) {
		super();
		this.data = data;
	}
	
}
