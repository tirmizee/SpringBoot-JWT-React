package com.tirmizee.jpa.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Pratya Yeekhaday
 *
 * @param <T>
 */
public abstract class MapParameterSpecification <T> implements Specification<T> {
	
	private static final long serialVersionUID = 1L;
	
	protected Map<String, Object> mapParameter;
	protected List<Predicate> predicates;

	public MapParameterSpecification(Map<String, Object> mapParameter) {
		this.mapParameter = mapParameter;
		this.predicates = new ArrayList<Predicate>();
	}
	
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	public Predicate toParallelPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.parallelStream().toArray(Predicate[]::new));
	}
	
}
