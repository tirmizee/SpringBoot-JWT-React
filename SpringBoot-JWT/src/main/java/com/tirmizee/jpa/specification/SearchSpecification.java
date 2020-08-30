package com.tirmizee.jpa.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;

/**
 * @author Pratya Yeekhaday
 * 
 * @param <B> Object type of search
 * @param <T> Domain object
 */

@Getter
public abstract class SearchSpecification<S,T> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	protected S search;
	
	public SearchSpecification(S search) {
		this.search = search;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return this.toPredicate(root, query, criteriaBuilder, search);
	}
	
	public abstract Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, S searchBody);
	
	protected Predicate buildPredicate(List<Predicate> predicates, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
}
