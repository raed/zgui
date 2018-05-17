package concept.predefined.web;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.web.LinkUtils.Link;

public class ThymeleafEntityModel<T extends BaseEntity> extends AThymeleafModel<T> {

	private T entity;

	@SuppressWarnings("unchecked")
	public ThymeleafEntityModel(T entity, DBService service) {
		super((Class<T>) entity.getClass(), service);
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}

	public Link getEditLink() {
		return LinkUtils.createEditLinkFor(entity);
	}

	public Link getDeleteLink() {
		return LinkUtils.createDeleteLinkFor(entity);
	}

	public Link getSaveLink() {
		return LinkUtils.createSaveLinkFor(entity);
	}

}
