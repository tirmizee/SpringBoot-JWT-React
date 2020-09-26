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
import com.tirmizee.jpa.specification.SearchCriteria;
import com.tirmizee.jpa.specification.SearchPageSpecification;

public class SearchUserSpecification extends SearchPageSpecification<SearchCriteria<UserCriteria>, User> {

	private static final long serialVersionUID = 1L;

	public SearchUserSpecification(SearchCriteria<UserCriteria> serach) {
		super(serach);
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
			SearchCriteria<UserCriteria> searchCriteria) {
		
//		Join<User, Profile> profile = root.join("profile", JoinType.INNER);
		
		UserCriteria userCriteria = searchCriteria.getSearch(); 
		List<Predicate> predicates = new LinkedList<>();
			
		String username = userCriteria.getUsername(); 
		if (!StringUtils.isBlank(username)) {
			Predicate likeUsername = criteriaBuilder.like(root.get("username"), "%" + username + "%");
			predicates.add(likeUsername);
		}
		
		String firstName = userCriteria.getFirstName();
		if (!StringUtils.isBlank(firstName)) {
			Predicate likeFirstname = criteriaBuilder.like(root.get("profile.firstName"), "%" + firstName + "%");
			predicates.add(likeFirstname);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
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
