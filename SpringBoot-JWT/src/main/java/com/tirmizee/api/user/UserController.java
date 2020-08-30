package com.tirmizee.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.api.user.data.UserCriteria;
import com.tirmizee.api.user.data.UserDTO;
import com.tirmizee.component.VarargMessageSource;
import com.tirmizee.config.security.CurrentUser;
import com.tirmizee.jpa.entities.Profile;
import com.tirmizee.jpa.entities.User;
import com.tirmizee.jpa.repositories.UserRepository;
import com.tirmizee.jpa.specification.SearchPageable;
import com.tirmizee.jpa.specification.custom.SearchUserSpecification;
import com.tirmizee.mapper.UserMapper;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	private UserMapper userMapper = UserMapper.INSTANCE;
	
	@Autowired
	private VarargMessageSource messageSource;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('P001')")
	@GetMapping(path = "/profile")
	public UserDTO getProfile() {
		System.out.println(messageSource.getVargMessage("app.auther"));
		return userMapper.toDTO(userRepository.findByUsername(CurrentUser.getDetail().getUsername()));
	}
	
	@PostMapping(path = "/all")
	public Page<UserDTO> findAll(@RequestBody SearchPageable<UserCriteria> search) {
		System.out.println(search.getSearch());
		SearchUserSpecification specification = new SearchUserSpecification(search);
		Page<User> page = userRepository.findAll(specification, specification.getPageable());
		Page<UserDTO> pageDTO = page.map((user) -> {
			
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setUsername(user.getUsername());
			userDTO.setEnabled(user.getEnabled());
			
			Profile profile = user.getProfile();
			userDTO.setEmail(profile.getEmail());
			userDTO.setCitizenId(profile.getCitizenId());
			userDTO.setFirstName(profile.getFirstName());
			userDTO.setLastName(profile.getLastName());
			
			return userDTO;
			
		});
		return pageDTO;
	}
	
}
