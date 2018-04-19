package concept.predefined.web;

import concept.predefined.BaseEntity;
import concept.predefined.web.LinkUtils.Link;

public class ThymeleafEntityModel<T extends BaseEntity> extends AThymeleafModel<T> {

	private T entity;

	@SuppressWarnings("unchecked")
	public ThymeleafEntityModel(T entity) {
		super((Class<T>) entity.getClass());
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}

	public Link getEditLink() {
		return LinkUtils.createEditLinkFor(entity);
	}
	public Link getSaveLink() {
		return LinkUtils.createSaveLinkFor(entity);
	}

}
