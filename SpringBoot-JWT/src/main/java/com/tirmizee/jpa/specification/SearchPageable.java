package com.tirmizee.jpa.specification;

import lombok.Data;


/**
 * @author Pratya Yeekhaday
 *
 */
@Data
public class SearchPageable<B> {
	 	
	 private Integer page = 0;
	 private Integer size = 10;
	 private String sort;
	 private String sortField;
	 private B search;

}
