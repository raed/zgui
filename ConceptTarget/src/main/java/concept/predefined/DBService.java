package concept.predefined;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class DBService {

	@PersistenceContext
	private EntityManager entityManager;

	public <T extends BaseEntity> List<T> getAll(Class<T> klasse) {
		return entityManager.createQuery("SELECT a FROM " + klasse.getSimpleName() + " a", klasse).getResultList();
	}

	public <T extends BaseEntity> Optional<T> getByID(Object id, Class<T> klasse) {
//		retr.s
		T object = entityManager.find(klasse, id); 
		Optional<T> retr = Optional.ofNullable(object);
		return retr;
	}

	@Transactional
	public <T extends BaseEntity> T saveOrUpdate(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public <T extends BaseEntity> void delete(T entity) {
		entityManager.remove(entity);
	}
}
