package com.tirmizee.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageError {
	
	private String errorCode;
	private String errorDesc;

}
