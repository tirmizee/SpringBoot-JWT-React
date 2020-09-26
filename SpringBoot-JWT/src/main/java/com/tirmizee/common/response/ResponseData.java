package com.tirmizee.common.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Pratya Yeekhday
 *
 * @param <T> Object Type to return
 */
@Data
public class ResponseData<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected boolean status;
	protected String messageCode;
	protected String messageDesc;
	
	protected T data;
	
	public static <T> ResponseData<T> success(T data) {
		ResponseData<T> responseData = new ResponseData<T>();
		responseData.setStatus(true);
		responseData.setData(data);
		return responseData;
	}
	
	public static <T> ResponseData<T> failure(T data) {
		ResponseData<T> responseData = new ResponseData<T>();
		responseData.setStatus(false);
		responseData.setData(data);
		return responseData;
	}

}
