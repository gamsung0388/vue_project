package com.example.demo.controller.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class ApiResult<T> {
	@NonNull
	private T data;
	private String message;
	
	public ApiResult() { }
	
	public ApiResult(T data) {
		this.data = data;
	}
	
	@Builder
	public ApiResult(T data, String message) {
		this.data = data;
		this.message = message;
	}
}
