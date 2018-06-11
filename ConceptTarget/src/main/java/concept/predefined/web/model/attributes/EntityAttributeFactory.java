package concept.predefined.web.model.attributes;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.NotImplementedException;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.marker.ExtraAttributes;

public class EntityAttributeFactory extends AEntityAttribute {

	public EntityAttributeFactory(String name, Method getter, Method setter, DBService service, String parents) {
		super(name, getter, setter, service, parents);
	}

	public AEntityAttribute createAttribute() {
		if (this.isComplex()) {
			return new ComplexEntityAttribute(this);
		}
		if (this.isEntityCollection()) {
			return new EntityCollectionAttribute(this);
		}
		if (this.isSimpleAttribute()) {
			return new SimpleEntityAttribute(this);
		}
		if (this.isPrimitiveCollection()) {
			return new SimpleCollectionAttribute(this);
		}
		if (this.isExtraAttribute()) {
			return new ExtraEntityAttributes(this);
		}
		throw new IllegalStateException("Unknown Attribute: " + getDisplayName());
	}

	private boolean isSimpleAttribute() {
		if (getType().equals(String.class)) {
			return true;
		}
		if (getType().equals(Date.class)) {
			return true;
		}
		if (getType().isPrimitive()) {
			return true;
		}
		return false;
	}

	private boolean isExtraAttribute() {
		return getAttributeGetter().isAnnotationPresent(ExtraAttributes.class);
	}

	private boolean isComplex() {
		return BaseEntity.class.isAssignableFrom(getType());
	}

	private boolean isCollection() {
		return Collection.class.isAssignableFrom(getType());
	}

	private boolean isEntityCollection() {
		return isCollection() && BaseEntity.class.isAssignableFrom(getCollectionType());
	}

	private boolean isPrimitiveCollection() {
		return isCollection() && !(isEntityCollection());
	}

	@Override
	public String getHTMLInput(BaseEntity object) {
		throw new NotImplementedException();
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		throw new NotImplementedException();
	}

}