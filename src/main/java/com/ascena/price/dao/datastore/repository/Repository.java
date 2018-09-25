package com.ascena.price.dao.datastore.repository;

import java.util.List;

/**
 * A base interface for the
 * <a href="http://martinfowler.com/eaaCatalog/repository.html">Repository</a>
 * pattern.
 *
 * @param <E>
 *            The entity type
 * @param <K>
 *            The key type of the entity
 */
public interface Repository<E, K> {

	/**
	 * Load the entity with the given id
	 *
	 * @param key
	 * @return the entity, or null if no entity exists
	 */
	public E get(K key);

	/**
	 * Load the entities with the given ids
	 *
	 * @param keys
	 * @return a list containing an entry for each corresponding id, containing the
	 *         entity or null if none exists
	 */
	@SuppressWarnings("unchecked")
	public List<E> get(K... keys);

	/**
	 * Load the entities with the given ids
	 *
	 * @param keys
	 * @return a list containing an entry for each corresponding id, containing the
	 *         entity or null if none exists
	 */
	public List<E> get(Iterable<K> keys);

	/**
	 * List up to count entities. This will load all entities into memory, so should
	 * only be used where the number of entities is constrained.
	 *
	 * @param count
	 * @return
	 */
	public List<E> list(int count);

	/**
	 * Load all entities whose field has the value of the given object. Note that
	 * the given field must be indexed for anything to be returned. This will load
	 * all entities into memory, so should only be used where the number of entities
	 * is constrained.
	 *
	 * @param field
	 * @param value
	 * @return
	 */
	public List<E> getByField(String field, Object value);

	/**
	 * Load all entities who field has the values of any of the given objects. Note
	 * that the given field must be indexed for anything to be returned. This will
	 * load all entities into memory, so should only be used where the number of
	 * entities is constrained.
	 *
	 * @param field
	 * @param values
	 * @return
	 */
	public List<E> getByField(String field, List<?> values);

}
