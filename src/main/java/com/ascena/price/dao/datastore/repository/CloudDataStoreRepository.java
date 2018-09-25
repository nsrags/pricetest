package com.ascena.price.dao.datastore.repository;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.CollectionUtils;

import com.ascena.price.exceptions.RepositoryException;
import com.atomicleopard.expressive.ETransformer;
import com.atomicleopard.expressive.Expressive;
import com.atomicleopard.expressive.transform.CollectionTransformer;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

/**
 * Cloud Data Store Repository
 * 
 * @author SMeenavalli
 *
 * @param <E>
 * @param <K>
 */
public abstract class CloudDataStoreRepository<E, K> implements Repository<E, K> {
	protected Class<E> entityType;
	protected Field idField;
	protected ETransformer<K, Key<E>> toKey;
	protected ETransformer<E, Key<E>> toKeyFromEntity;
	protected CollectionTransformer<E, Key<E>> toKeysFromEntities;
	protected ETransformer<Key<E>, K> fromKey;
	protected CollectionTransformer<Key<E>, K> fromKeys;

	/**
	 * 
	 * @param entityType
	 * @param toKey
	 * @param fromKey
	 */
	public CloudDataStoreRepository(Class<E> entityType, ETransformer<K, Key<E>> toKey,
			ETransformer<Key<E>, K> fromKey) {
		this.entityType = entityType;
		this.toKey = toKey;
		this.fromKey = fromKey;

	}

	/**
	 * 
	 * @param keys
	 * @return
	 */
	protected E loadInternal(Key<E> keys) {
		return ofy().load().key(keys).now();
	}

	/**
	 * 
	 * @param keys
	 * @return
	 */
	protected List<E> loadInternal(Iterable<Key<E>> keys) {
		if (Expressive.isEmpty(keys)) {
			return Collections.<E>emptyList();
		}
		Map<Key<E>, E> results = ofy().load().keys(keys);
		return Expressive.Transformers.transformAllUsing(Expressive.Transformers.usingLookup(results)).from(keys);
	}

	public E get(K key) {
		Key<E> ofyKey = toKey.from(key);
		return loadInternal(ofyKey);
	}

	/**
	 * 
	 * @param keys
	 * @return
	 */
	public List<E> get(K... keys) {
		return new ArrayList<>();
	}

	public List<E> get(Iterable<K> keys) {
		List<Key<E>> keysList = new ArrayList<>();
		keys.forEach(key -> {
			Key<E> ofyKey = toKey.from(key);
			keysList.add(ofyKey);
		});
		return loadInternal(keysList);
	}

	/**
	 * 
	 */
	public List<E> list(int count) {
		return new ArrayList<>();
	}

	/**
	 * 
	 */
	@Override
	public List<E> getByField(String field, List<?> values) {
		List<E> resultList = new ArrayList<>();
		values.forEach(value -> {
			List<E> result = getByField(field, value);
			if (!CollectionUtils.isEmpty(result) && result.get(0) != null) {
				resultList.add(result.get(0));
			}

		});
		return resultList;
	}

	/**
	 * 
	 */
	public List<E> getByField(String field, Object value) {
		return ofy().load().type(entityType).filter(field, value).list();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	protected Key<E> key(E entity) {
		return Key.create(entity);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	protected boolean hasId(E entity) {
		try {
			return idField.get(entity) != null;
		} catch (IllegalArgumentException e) {
			throw new RepositoryException(e,
					"RepositoryException : Unable to determine if an id exists for a %s - %s: %s",
					entityType.getSimpleName(), entity, e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RepositoryException(e,
					"IllegalAccessException :Unable to determine if an id exists for a %s - %s: %s",
					entityType.getSimpleName(), entity, e.getMessage());
		}
	}

	/**
	 * 
	 * @param entityType
	 * @return
	 */
	protected Field getIdField(Class<E> entityType) {
		try {
			String idFieldName = ObjectifyService.factory().getMetadata(entityType).getKeyMetadata().getIdFieldName();
			return FieldUtils.getField(entityType, idFieldName, true);
		} catch (IllegalArgumentException e) {
			throw new RepositoryException(e, "Unable to determine id field for type %s: %s",
					entityType.getClass().getName(), e.getMessage());
		}
	}

}
