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
public abstract class SearchPageSpecification<B extends SearchPageable, T> extends SearchBodySpecification<B,T> {

	private static final long serialVersionUID = 1L;
	
	public static final String DEFUALT_ASC = "A";
	public static final String DEFUALT_DESC = "D";

	public SearchPageSpecification(B serachBody) {
		super(serachBody);
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
		switch (StringUtils.trimToEmpty(sort)) {
			case DEFUALT_ASC  : return Sort.by(Sort.Order.asc(sortField));
			case DEFUALT_DESC : return Sort.by(Sort.Order.desc(sortField));
			default : return Sort.by(Sort.Order.asc(sortField));
		}
	}
	
	public Pageable getPageable() {
		Integer page = getSearchBody().getPage();
		Integer size = getSearchBody().getSize();
		String sort = getSearchBody().getSort();
		String sortField = sortProperty(getSearchBody().getSortField());
		return PageRequest.of(page, size, buildSort(sort, sortField));
	}

}
