package com.ascena.price.dao.datastore.repository;

/**
 * 
 * @author SMeenavalli
 *
 * @param <T>
 */
public interface AsyncResult<T> {
	/**
	 * 
	 * @return T
	 */
	T complete();
}
