package com.tirmizee.jpa.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

/**
 * @author Pratya Yeekhaday
 * 
 * @param <B> Object type to search
 * @param <T> Domain object
 */
@Data
public abstract class SearchBodySpecification<B,T> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	protected B searchBody;
	protected List<Predicate> predicates;
	
	public SearchBodySpecification(B serachBody) {
		this.searchBody = serachBody;
		this.predicates = new ArrayList<Predicate>();
	}
	
	public Predicate buildPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	public Predicate buildParallelPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.parallelStream().toArray(Predicate[]::new));
	}
	
}
