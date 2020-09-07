package com.tirmizee.jpa.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria<T> extends SearchPageable {
	
	private T search;
	
}
