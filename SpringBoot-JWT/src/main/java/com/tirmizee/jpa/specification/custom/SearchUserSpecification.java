package com.tirmizee.jpa.specification.custom;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.tirmizee.api.user.data.UserCriteria;
import com.tirmizee.jpa.entities.Profile;
import com.tirmizee.jpa.entities.User;
import com.tirmizee.jpa.specification.SearchPageSpecification;
import com.tirmizee.jpa.specification.SearchPageable;

public class SearchUserSpecification extends SearchPageSpecification<SearchPageable<UserCriteria>, User> {

	private static final long serialVersionUID = 1L;

	public SearchUserSpecification(SearchPageable<UserCriteria> serachBody) {
		super(serachBody);
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
			SearchPageable<UserCriteria> searchBody) {
		
		Join<User, Profile> profile = root.join("profile", JoinType.INNER);
		
		UserCriteria userCriteria = searchBody.getSearch(); 
		List<Predicate> predicates = new LinkedList<>();
			
		String username = userCriteria.getUsername(); 
		if (!StringUtils.isBlank(username)) {
			predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
		}
		
		String firstName = userCriteria.getFirstName();
		if (!StringUtils.isBlank(firstName)) {
			predicates.add(criteriaBuilder.like(profile.get("firstName"), "%" + firstName + "%"));
		}
		
		return super.buildPredicate(predicates, criteriaBuilder);
	}
	
	@Override
	protected String sortProperty(String sortField) {
		
		if (sortField == null) { return "userId"; }
		
		switch (sortField) {
			case "email" : return "profile.email";
			case "firstName" : return "profile.firstName";
			default : return sortField;
		}
		
	}

}
