package concept.predefined.web;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import concept.predefined.BaseEntity;

@Component
public class EntityUtil {

	@Autowired
	private EntityManager em;

	public BaseEntity createObject(String className) {
		BaseEntity object;
		try {
			object = findClassByName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	public Class<BaseEntity> findClassByName(String name) {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		// @formatter:off
		return (Class<BaseEntity>) entities.stream()
				.filter(e -> e.getJavaType()
						.getSimpleName()
						.equalsIgnoreCase(name))
				.map(EntityType::getJavaType)
				.findFirst()
				.get();
		// @formatter:on
	}
}
