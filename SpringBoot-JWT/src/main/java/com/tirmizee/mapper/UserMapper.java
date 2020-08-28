package com.tirmizee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.tirmizee.api.user.data.UserDTO;
import com.tirmizee.jpa.entities.User;

@Mapper
public interface UserMapper extends GenericMapper<UserDTO, User> {
	
	static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
