package concept.predefined.web;

import java.util.Collection;

import concept.predefined.BaseEntity;

public class ThymeleafTableModel<T extends BaseEntity> extends AThymeleafModel<T> {

	private Collection<T> objects;

	public ThymeleafTableModel(Class<T> clazz) {
		super(clazz, null);
	}
	
	public void setObjects(Collection<T> objects) {
		this.objects = objects;
	}

	public Collection<T> getObjects() {
		return objects;
	}

}
