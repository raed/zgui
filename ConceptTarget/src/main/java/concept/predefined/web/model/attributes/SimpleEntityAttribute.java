package concept.predefined.web.model.attributes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Id;

import concept.predefined.BaseEntity;
import concept.predefined.web.LinkUtils;

public class SimpleEntityAttribute extends AEntityAttribute {

	public SimpleEntityAttribute(EntityAttributeFactory factory) {
		super(factory);
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		if (getAttributeGetter().isAnnotationPresent(Id.class)) {
			return LinkUtils.createLinkFor(object).createHTMLLink();
		}
		Object value = getValueObject(object);
		if (value == null) {
			return "";
		}
		if (getType().equals(Date.class)) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			return sdf.format(value);
		}

		return value.toString();
	}

	@Override
	public String getHTMLInput(BaseEntity object) {

		if (isTextInputType()) {
			String simpleInput = "<input id=\"%1$s\" name=\"%1$s\" value=\"%2$s\">";
			return String.format(simpleInput, getAttributePath(), getHTMLValue(object));
		}

		if (getType().equals(boolean.class)) {
			String simpleInput = "<input type=\"checkbox\" class=\"check\" id=\"%1$s\" name=\"%1$s\" %2$s>";
			String checked = (boolean) getValueObject(object) ? "checked" : "";
			return String.format(simpleInput, getAttributePath(), checked);
		}

		throw new IllegalStateException("Not here: " + object + " " + getDisplayName() + " " + this.getClass().getSimpleName());
	}

	private boolean isTextInputType() {
		if (getType().equals(String.class)) {
			return true;
		}
		if (getType().equals(Date.class)) {
			return true;
		}
		if (getType().isPrimitive()) {
			if (getType().equals(boolean.class)) {
				return false; // Boolean is checkbox
			}
			return true;
		}
		return false;
	}
}