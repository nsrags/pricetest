
package com.ascena.price.dao.datastore.repository;

import java.util.List;

/**
 * An interface extension for {@link Repository} which enables asynchronous
 * interfaces for save and deleteUnit operations.
 *
 * @param <E>
 *            The entity type
 * @param <K>
 *            The key type of the entity
 */
public interface AsyncRepository<E, K> extends Repository<E, K> {
	AsyncResult<E> putAsync(final E entity);

	/**
	 * Save the given entities.
	 *
	 * @param entities
	 * @return an async result to complete the save operation
	 */
	@SuppressWarnings("unchecked")
	AsyncResult<List<E>> putAsync(E... entities);

	/**
	 * Save the given entities.
	 *
	 * @param entities
	 * @return an async result to complete the save operation
	 */
	AsyncResult<List<E>> putAsync(final List<E> entities);

	/**
	 * Delete the entity with the given id
	 *
	 * @param key
	 * @return an async operation used to complete the deleteUnit operation
	 */
	AsyncResult<Void> deleteByKeyAsync(K key);

	/**
	 * Delete the entities with the given ids
	 *
	 * @param keys
	 * @return an async operation used to complete the deleteUnit operation
	 */
	@SuppressWarnings("unchecked")
	AsyncResult<Void> deleteByKeyAsync(K... keys);

	/**
	 * Delete the entities with the given ids
	 *
	 * @param ids
	 * @return an async operation used to complete the deleteUnit operation
	 */
	AsyncResult<Void> deleteByKeyAsync(Iterable<K> ids);

	/**
	 * Delete the given entity
	 *
	 * @param entity
	 * @return an async operation used to complete the deleteUnit operation
	 */
	AsyncResult<Void> deleteAsync(E entity);

	/**
	 * Delete the given entities
	 *
	 * @param entities
	 * @return an async operation used to complete the deleteUnit operation
	 */
	@SuppressWarnings("unchecked")
	AsyncResult<Void> deleteAsync(E... entities);

	/**
	 * Delete the given entities
	 *
	 * @param entities
	 * @return an async operation used to complete the deleteUnit operation
	 */
	AsyncResult<Void> deleteAsync(Iterable<E> entities);
}
