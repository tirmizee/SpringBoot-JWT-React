package com.tirmizee.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The primary key class for the ROLE_MAP_PERMISSION database table.
 * 
 */
@Data
@NoArgsConstructor
@Embeddable
public class RoleMapPermissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID")
	private Long roleId;

	@Column(name="PER_ID")
	private Long perId;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoleMapPermissionPK)) {
			return false;
		}
		RoleMapPermissionPK castOther = (RoleMapPermissionPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.perId == castOther.perId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime + ((int) (this.perId ^ (this.perId >>> 32)));
		return hash;
	}
}