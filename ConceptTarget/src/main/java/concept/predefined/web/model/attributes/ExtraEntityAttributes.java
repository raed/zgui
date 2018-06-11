package concept.predefined.web.model.attributes;

import java.util.Map;

import concept.predefined.BaseEntity;
import concept.predefined.dynamic.Attribute;

public class ExtraEntityAttributes extends AEntityAttribute {

	public ExtraEntityAttributes(EntityAttributeFactory factory) {
		super(factory);
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		return getValueObject(object).size() + "";
	}

	@Override
	public String getHTMLInput(BaseEntity object) {
		return getHTMLValue(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Attribute, Object> getValueObject(BaseEntity object) {
		return (Map<Attribute, Object>) super.getValueObject(object);
	}

}
