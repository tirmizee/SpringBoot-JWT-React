package com.tirmizee.jpa.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Pratya Yeekhaday
 * 
 * @param <B> Object type of SearchPageable
 * @param <T> Domain object
 */
public abstract class SearchPageSpecification<S extends SearchPageable, T> extends SearchSpecification<S,T> {

	private static final long serialVersionUID = 1L;
	
	public static final String DEFUALT_DERECTION_ASC = "A";
	public static final String DEFUALT_DERECTION_DESC = "D";

	public SearchPageSpecification(S serachPage) {
		super(serachPage);
	}
	
	/**
	 * OVerride this method. if you want to customize the sort property.
	 */
	protected String sortProperty(String sortField) {
		return sortField;
	}
	
	/**
	 * OVerride this method. if you want to customize the sort directions.
	 */
	protected Sort buildSort(String sort, String sortField) {
		switch (sort) {
			case DEFUALT_DERECTION_ASC  : return Sort.by(Sort.Order.asc(sortField));
			case DEFUALT_DERECTION_DESC : return Sort.by(Sort.Order.desc(sortField));
			default : return Sort.by(Sort.Order.asc(sortField));
		}
	}
	
	/**
	 * OVerride this method. if you want to customize Pageable.
	 */
	public Pageable getPageable() {
		SearchPageable searchPageable = super.getSearch();
		Integer page = searchPageable.getPage();
		Integer size = searchPageable.getSize();
		String sort = StringUtils.defaultString(searchPageable.getSort(), DEFUALT_DERECTION_ASC);
		String sortField = StringUtils.defaultString(this.sortProperty(searchPageable.getSortField()), "id");
		return PageRequest.of(page, size, buildSort(sort, sortField));
	}

}
