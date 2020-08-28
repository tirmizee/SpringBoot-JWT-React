package com.tirmizee.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the ROLE_MAP_PERMISSION database table.
 * 
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="ROLE_MAP_PERMISSION")
@NamedQuery(name="RoleMapPermission.findAll", query="SELECT r FROM RoleMapPermission r")
public class RoleMapPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleMapPermissionPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

}