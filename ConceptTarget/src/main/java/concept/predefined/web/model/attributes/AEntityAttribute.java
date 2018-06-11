package concept.predefined.web.model.attributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;

public abstract class AEntityAttribute implements Comparable<AEntityAttribute> {

	public static final String	NA_DISPLAY	= "N/A";

	private String				displayName;
	private Method				attributeGetter;
	private Method				attributeSetter;

	private DBService			service;

	private String				parents;

	protected AEntityAttribute(String displayName, Method attributeGetter, Method attributeSetter, DBService service, String parents) {
		this.displayName = displayName;
		this.attributeGetter = attributeGetter;
		this.attributeSetter = attributeSetter;
		this.parents = parents;
		this.service = service;
	}

	public AEntityAttribute(EntityAttributeFactory factory) {
		this(factory.getDisplayName(), factory.getAttributeGetter(), factory.getAttributeSetter(), factory.getService(), factory.getParents());
	}

	public Method getAttributeGetter() {
		return attributeGetter;
	}

	public Method getAttributeSetter() {
		return attributeSetter;
	}

	public String getParents() {
		return parents;
	}

	public void setParent(String parents) {
		this.parents = parents;
	}

	protected DBService getService() {
		return service;
	}

	public String getDisplayName() {
		return displayName;
	}

	public boolean isReadOnly() {
		return attributeSetter == null;
	}

	public String getAttributePath() {
		return parents + displayName;
	}

	public Class<?> getType() {
		return attributeGetter.getReturnType();
	}

	public abstract String getHTMLInput(BaseEntity object);

	public abstract String getHTMLValue(BaseEntity object);

	public Class<?> getCollectionType() {
		ParameterizedType collectionType = (ParameterizedType) attributeGetter.getGenericReturnType();
		return (Class<?>) collectionType.getActualTypeArguments()[0];
	}

	public Object getValueObject(BaseEntity object) {
		try {
			return attributeGetter.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public int compareTo(AEntityAttribute o) {
		return this.displayName.compareTo(o.displayName);
	}
}