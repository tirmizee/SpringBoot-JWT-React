package com.tirmizee.api.authen.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String token;

}
