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
public abstract class SearchPageSpecification<S extends SearchPageable<?>, T> extends SearchSpecification<S,T> {

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
		switch (StringUtils.trimToEmpty(sort)) {
			case DEFUALT_DERECTION_ASC  : return Sort.by(Sort.Order.asc(sortField));
			case DEFUALT_DERECTION_DESC : return Sort.by(Sort.Order.desc(sortField));
			default : return Sort.by(Sort.Order.asc(sortField));
		}
	}
	
	public Pageable getPageable() {
		Integer page = getSearch().getPage();
		Integer size = getSearch().getSize();
		String sort = getSearch().getSort();
		String sortField = this.sortProperty(this.getSearch().getSortField());
		return PageRequest.of(page, size, buildSort(sort, sortField));
	}

}
