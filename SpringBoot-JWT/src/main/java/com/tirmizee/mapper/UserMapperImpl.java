package com.tirmizee.mapper;

import com.tirmizee.api.user.data.UserDTO;
import com.tirmizee.jpa.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        if ( dto.getUserId() != null ) {
            user.setUserId( dto.getUserId() );
        }
        user.setAccountNonExpired(dto.isAccountnonexpired() );
        user.setAccountNonLocked( dto.isAccountnonlocked() );
        user.setCredentialsexpiredDate( dto.getCredentialsexpiredDate() );
        user.setCredentialsNonExpired( dto.isCredentialsnonexpired() );
        user.setEnabled( dto.isEnabled() );
        user.setFirstLogin( dto.isFirstLogin() );
        user.setUsername( dto.getUsername() );

        return user;
    }

    @Override
    public UserDTO toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( entity.getUserId() );
        userDTO.setUsername( entity.getUsername() );
        if ( entity.getCredentialsexpiredDate() != null ) {
            userDTO.setCredentialsexpiredDate( new java.sql.Date( entity.getCredentialsexpiredDate().getTime() ) );
        }
        if ( entity.getCredentialsNonExpired() != null ) {
            userDTO.setCredentialsnonexpired( entity.getCredentialsNonExpired() );
        }
        if ( entity.getAccountNonExpired() != null ) {
            userDTO.setAccountnonexpired( entity.getAccountNonExpired() );
        }
        if ( entity.getAccountNonLocked() != null ) {
            userDTO.setAccountnonlocked( entity.getAccountNonLocked() );
        }
        if ( entity.getEnabled() != null ) {
            userDTO.setEnabled( entity.getEnabled() );
        }
        if ( entity.getFirstLogin() != null ) {
            userDTO.setFirstLogin( entity.getFirstLogin() );
        }

        return userDTO;
    }

    @Override
    public List<User> toEnties(List<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDTO userDTO : dtos ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDtos(List<User> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entities.size() );
        for ( User user : entities ) {
            list.add( toDTO( user ) );
        }

        return list;
    }
}
