package com.tirmizee.jpa.specification;

import org.springframework.data.jpa.domain.Specification;


/**
 * @author Pratya Yeekhaday
 *
 * @param <T>
 */

public abstract class SearchCriteriaSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -4483421821450268917L;
	
	protected SearchCriteria searchCriteria;

	public SearchCriteriaSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
