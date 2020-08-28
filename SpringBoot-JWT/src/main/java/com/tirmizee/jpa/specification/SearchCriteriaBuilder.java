package com.tirmizee.jpa.specification;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class SearchCriteriaBuilder {
	
	private List<SearchCriteria> searchCriterias;

	public SearchCriteriaBuilder() {
		this.searchCriterias = new ArrayList<SearchCriteria>();
	}
	
	public SearchCriteriaBuilder(List<SearchCriteria> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
	
	public SearchCriteriaBuilder with(String key, Object value) {
		searchCriterias.add(new SearchCriteria(key, null, value));
		return this;
	}
	
	public SearchCriteriaBuilder with(String key,String operation, Object value) {
		searchCriterias.add(new SearchCriteria(key, operation, value));
		return this;
	}
	
	public SearchCriteriaBuilder with(SearchCriteria searchCriteria) {
		searchCriterias.add(searchCriteria);
		return this;
	}
	
	public SearchCriteriaBuilder withPredicate(String predicate, String key, Object value) {
		searchCriterias.add(new SearchCriteria(predicate, key, null, value));
		return this;
	}
	
	public SearchCriteriaBuilder withPredicate(String predicate, String key, String operation, Object value) {
		searchCriterias.add(new SearchCriteria(predicate, key, operation, value));
		return this;
	}

	/**
	 * Build specification. 
	 * @author Pratya Yeekhaday
	 * @param clazz  Specification type to return.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> Specification<T> build(Class<? extends SearchCriteriaSpecification<T>> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		if (searchCriterias.size() == 0) return null;
		
		List<Specification<T>> Specifications = new ArrayList<Specification<T>>();

		for (SearchCriteria searchCriteria : searchCriterias) {
			Specifications.add(clazz.getConstructor(SearchCriteria.class).newInstance(searchCriteria));
		}

		Specification<T> specification = Specifications.get(0);
		for (int i = 1; i < searchCriterias.size(); i++) {
			 specification = searchCriterias.get(i).isOrPredicate()
				 ? Specification.where(specification).or(Specifications.get(i)) 
				 : Specification.where(specification).and(Specifications.get(i));
        }     

		return specification;
	}
	
}
