package com.tirmizee.api.user.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserCriteria {

	private String username;
	private String tel;
	private String firstName;
	private String lastName;
	
}
