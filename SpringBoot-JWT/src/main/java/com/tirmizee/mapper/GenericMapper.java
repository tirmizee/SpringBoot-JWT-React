package com.tirmizee.mapper;

import java.util.List;

/**
 * @author Pratya Yeekhaday
 *
 * @param <D> Data Transfer Object
 * @param <E> Entity
 */
public interface GenericMapper<D,E> {
	
	E toEntity(D dto);
	
	D toDTO(E entity);
	
	List<E> toEnties(List<D> dtos);
	
	List<D> toDtos(List<E> entities);
	
}
