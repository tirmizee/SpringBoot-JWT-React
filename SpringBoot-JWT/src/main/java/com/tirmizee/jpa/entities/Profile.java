package com.tirmizee.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the PROFILE database table.
 * 
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROFILE_ID")
	private Long profileId;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="CITIZEN_ID")
	private String citizenId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Lob
	@Column(name="PROFILE_BYTE")
	private byte[] profileByte;

	@Column(name="PROFILE_IMAGE")
	private String profileImage;

	@Column(name="SUB_DISTRICT_CODE")
	private String subDistrictCode;

	private String tel;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updateDate;

	@OneToMany(mappedBy="profile")
	private List<User> users;

	public User addUser(User user) {
		getUsers().add(user);
		user.setProfile(this);
		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setProfile(null);
		return user;
	}

}