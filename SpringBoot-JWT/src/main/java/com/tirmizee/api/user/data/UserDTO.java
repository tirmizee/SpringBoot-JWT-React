package com.tirmizee.api.user.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private Long userId;
	private String username;
	private java.sql.Date credentialsexpiredDate;
	private boolean credentialsnonexpired;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private boolean firstLogin;
	private Integer profileId;
	private String profileImage;
	private String lastName;
	private String firstName;
	private String email;
	private String tel;
	private Integer roleId;
	private String branchName;
	private String roleName;
	private String roleCode;
	private String citizenId;
	
}
