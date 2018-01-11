package de.xenocraft.exp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class DAO {

	@PersistenceContext
	private EntityManager entityManager;

	public <T> List<T> getAll(Class<T> klasse) {
		return entityManager.createQuery("SELECT a FROM " + klasse.getSimpleName() + " a", klasse).getResultList();
	}

	public <T> T getByID(Object id, Class<T> klasse) {
		return entityManager.find(klasse, id);
	}

	@Transactional
	public <T> T saveOrUpdate(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public <T> void delete(T entity) {
		entityManager.remove(entity);
	}
}
