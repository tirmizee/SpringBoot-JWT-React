package com.tirmizee.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tirmizee.jpa.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	@Query(name="PERMISSION.FIND_BY_USERNAME")
	List<Permission> findByUsername(@Param("username") String username);
	
}
