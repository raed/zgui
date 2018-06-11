package concept.predefined.web.model.attributes;

import java.util.Collection;
import java.util.List;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import concept.predefined.BaseEntity;
import concept.predefined.web.model.ThymeleafTableModel;

public class EntityCollectionAttribute extends AEntityAttribute {

	public EntityCollectionAttribute(EntityAttributeFactory factory) {
		super(factory);
	}

	public boolean isEmpty(BaseEntity entity) {
		return getCollection(entity).isEmpty();
	}

	@SuppressWarnings("unchecked")
	public Collection<BaseEntity> getCollection(BaseEntity entity) {
		return (Collection<BaseEntity>) getValueObject(entity);
	}

	@SuppressWarnings("unchecked")
	public ThymeleafTableModel<BaseEntity> getTableModel(BaseEntity entity) {
		ThymeleafTableModel<BaseEntity> tm = new ThymeleafTableModel<BaseEntity>((Class<BaseEntity>) getCollectionType());
		tm.setObjects(getCollection(entity));
		return tm;
	}

	@SuppressWarnings("unchecked")
	public Model getEditModel(BaseEntity entity) {
		Model m = new ExtendedModelMap();
		List<? extends BaseEntity> objects = getService().getAll((Class<? extends BaseEntity>) getCollectionType());
		m.addAttribute("allObjects", objects);
		m.addAttribute("objects", getCollection(entity));
		return m;
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		return "" + getCollection(object).size();
	}

	@Override
	public String getHTMLInput(BaseEntity object) {
		return getHTMLValue(object);
	}

}