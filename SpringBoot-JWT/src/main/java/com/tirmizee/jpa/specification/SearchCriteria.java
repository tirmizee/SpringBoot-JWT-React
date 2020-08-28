package com.tirmizee.jpa.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
	
	public static final String OR_PREDICATE_FLAG = "OR";
	public static final String AND_PREDICATE_FLAG = "AND";
	
	private String predicate;
	private String key;
	private String operation;
	private Object value;

	public SearchCriteria(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
		this.predicate = AND_PREDICATE_FLAG;
	}

	public boolean isOrPredicate() {
    	return OR_PREDICATE_FLAG.equalsIgnoreCase(predicate);
    }

	
}
