package concept.predefined.web.model.attributes;

import java.util.Collection;

import concept.predefined.BaseEntity;

public class SimpleCollectionAttribute extends AEntityAttribute {

	public SimpleCollectionAttribute(EntityAttributeFactory factory) {
		super(factory);
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		Collection<?> collection = (Collection<?>) getValueObject(object);
		return "" + collection.size();
	}

	@Override
	public String getHTMLInput(BaseEntity object) {
		return getHTMLValue(object);
	}

	@SuppressWarnings("unchecked")
	public Collection<Object> getCollection(BaseEntity object) {
		return (Collection<Object>) getValueObject(object);
	}
}
