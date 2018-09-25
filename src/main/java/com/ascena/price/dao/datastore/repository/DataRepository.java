
package com.ascena.price.dao.datastore.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ascena.price.exceptions.SysException;
import com.atomicleopard.expressive.ETransformer;
import com.googlecode.objectify.Key;

@SuppressWarnings("unchecked")
/**
 * 
 * @author SMeenavalli
 *
 * @param <E>
 */
public class DataRepository<E> extends CloudDataStoreRepository<E, String> {

	private static Logger logger = LoggerFactory.getLogger(DataRepository.class);

	/**
	 * 
	 * @param entityType
	 */
	public DataRepository(final Class<E> entityType) {
		super(entityType, fromString(entityType), toString(entityType));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public <T> T getById(final String id) throws SysException {
		T t = null;
		try {
			t = (T) this.get(id);

		} catch (Exception ex) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format(" Exception details %s", ex));
			}
			throw new SysException(ex.getMessage(), "PRC-SYS-EXP-DATASTORE");
		}

		return t;
	}

	public List<E> getById(final Iterable<String> keys) {

		return this.get(keys);
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	static <E> ETransformer<Key<E>, String> toString(final Class<E> type) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format(" type %s", type));
		}
		return Key::getName;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	static <E> ETransformer<String, Key<E>> fromString(final Class<E> type) {
		return from -> Key.create(type, from);
	}

}
