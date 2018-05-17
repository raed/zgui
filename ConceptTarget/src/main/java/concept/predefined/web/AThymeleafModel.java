package concept.predefined.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Id;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.web.LinkUtils.Link;

public abstract class AThymeleafModel<T extends BaseEntity> {

	protected AEntityAttribute				idAttribute;
	protected List<AEntityAttribute>		attributes;
	private List<ComplexEntityAttribute>	complexAttributes;
	private List<CollectionEntityAttribute>	collectionAttributes;
	protected Class<T>						targetClass;
	protected String						targetName;
	private String							parents	= "";

	protected DBService						service;

	public AThymeleafModel(Class<T> clazz, DBService service) {
		this.attributes = new ArrayList<>();
		this.targetClass = clazz;
		this.service = service;
		generateAttributes();
	}

	@Override
	public String toString() {
		return "ATHymeleafModel for " + targetClass + " target: " + targetName;
	}

	private void generateAttributes() {
		targetName = targetClass.getSimpleName();
		for (Method m : targetClass.getMethods()) {
			if (m.isAnnotationPresent(Invisible.class)) {
				continue; // Inivisble wird nicht angezeigt
			}
			if (m.getParameterCount() != 0) {
				continue; // Getter haben keine Argumente
			}
			if (!m.getName().startsWith("get")) {
				continue; // Getter fangen mit get an
			}
			String name = m.getName().substring(3, m.getName().length());
			// EntityAttribute col = new EntityAttribute(name, m, findSetter(m));
			if (name.equals("Class")) {
				continue; // Class uninteressant
			}

			EntityAttributeFactory eaf = new EntityAttributeFactory(name, m, findSetter(m));
			AEntityAttribute attr = eaf.createAttribute();
			if (name.equalsIgnoreCase("Id")) {
				idAttribute = attr;
				continue; // Id ist extra
			}
			addAttribute(attr);
		}
		if (idAttribute == null) {
			throw new IllegalArgumentException("Not a BaseEntity: " + targetClass);
		}
	}

	private Method findSetter(Method getter) {
		String name = "set" + getter.getName().substring(3, getter.getName().length());
		for (Method m : targetClass.getMethods()) {
			if (!m.getName().equals(name)) {
				continue;
			}
			if (!(m.getReturnType().equals(Void.TYPE))) {
				continue;
			}
			if (!(m.getParameterCount() == 1)) {
				continue;
			}
			return m;
		}

		return null;
	}

	public String getTargetName() {
		return targetName;
	}

	public AEntityAttribute getIdAttribute() {
		return idAttribute;
	}

	// public String getIDHTMLInput() {
	// String idInput = "<input type=\"hidden\" name=\"id\" id=\"id\" th:value=\"${entityModel.entity.id}\">";
	// return String.format(idInput, idAttribute.getAttributePath(), idAttribute.getValueObject(object))
	// }

	@SuppressWarnings("unchecked")
	public void addAttribute(AThymeleafModel<T>.AEntityAttribute attr) {
		Objects.requireNonNull(attr);
		attributes.add(attr);

		complexAttributes = new ArrayList<>();
		collectionAttributes = new ArrayList<>();
		for (AEntityAttribute eAttr : attributes) {
			if (eAttr instanceof AThymeleafModel.ComplexEntityAttribute) {
				complexAttributes.add((AThymeleafModel<T>.ComplexEntityAttribute) eAttr);
			}
			if (eAttr instanceof AThymeleafModel.CollectionEntityAttribute) {
				collectionAttributes.add((AThymeleafModel<T>.CollectionEntityAttribute) eAttr);
			}
		}

		Collections.sort(attributes);
	}

	public List<AEntityAttribute> getAttributes() {
		return attributes;
	}

	public List<ComplexEntityAttribute> getComplexAttributes() {
		return complexAttributes;
	}

	public List<CollectionEntityAttribute> getCollectionAttributes() {
		return collectionAttributes;
	}

	public Link getNewLink() {
		return LinkUtils.createAddLinkFor(targetClass);
	}

	public static String changeFirstLetter(String input, boolean cap) {
		if (input == null) {
			return null;
		}
		String output;
		if (cap) {
			output = input.substring(0, 1).toUpperCase();
		} else {
			output = input.substring(0, 1).toLowerCase();
		}
		if (input.length() > 1) {
			output += input.substring(1);
		}
		return output;
	}

	public void setParent(String parentName) {
		parents += parentName + ".";
	}

	public class SimpleEntityAttribute extends AEntityAttribute {

		public SimpleEntityAttribute(EntityAttributeFactory factory) {
			super(factory);
		}
	}

	public class ComplexEntityAttribute extends AEntityAttribute {

		public ComplexEntityAttribute(EntityAttributeFactory factory) {
			super(factory);
		}

		public ThymeleafEntityModel<BaseEntity> getEntityModel(T entity) {
			BaseEntity targetObject = (BaseEntity) getValueObject(entity);
			if (targetObject == null) {
				try {
					targetObject = (BaseEntity) getType().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			ThymeleafEntityModel<BaseEntity> entityModel = new ThymeleafEntityModel<BaseEntity>(targetObject, service);
			entityModel.setParent(getDisplayName());
			return entityModel;
		}

	}

	public class CollectionEntityAttribute extends AEntityAttribute {

		public CollectionEntityAttribute(AThymeleafModel<T>.EntityAttributeFactory factory) {
			super(factory);
		}

		public boolean isEmpty(T entity) {
			return getCollection(entity).isEmpty();
		}

		@SuppressWarnings("unchecked")
		public Collection<BaseEntity> getCollection(T entity) {
			return (Collection<BaseEntity>) getValueObject(entity);
		}

		@SuppressWarnings("unchecked")
		public ThymeleafTableModel<BaseEntity> getTableModel(T entity) {
			ThymeleafTableModel<BaseEntity> tm = new ThymeleafTableModel<BaseEntity>((Class<BaseEntity>) getCollectionType());
			tm.setObjects(getCollection(entity));
			return tm;
		}

		@SuppressWarnings("unchecked")
		public Model getEditModel(T entity) {
			Model m = new ExtendedModelMap();
			List<? extends BaseEntity> objects = service.getAll((Class<? extends BaseEntity>) getCollectionType());
			m.addAttribute("allObjects", objects);
			m.addAttribute("objects", getCollection(entity));
			return m;
		}

	}

	public class EntityAttributeFactory extends AEntityAttribute {

		public EntityAttributeFactory(String name, Method getter, Method setter) {
			super(name, getter, setter);
		}

		public AEntityAttribute createAttribute() {
			if (this.isComplex()) {
				return new ComplexEntityAttribute(this);
			}
			if (this.isEntityCollection()) {
				return new CollectionEntityAttribute(this);
			}
			if (this.isSimpleAttribute()) {
				return new SimpleEntityAttribute(this);
			}
			if (this.isCollection()) {
				return new SimpleEntityAttribute(this);
			}
			throw new IllegalStateException("Unknown Attribute: " + getDisplayName());
		}

	}

	public abstract class AEntityAttribute implements Comparable<AEntityAttribute> {

		public static final String	NA_DISPLAY	= "N/A";

		private String				displayName;
		private Method				attributeGetter;
		private Method				attributeSetter;

		public AEntityAttribute(String displayName, Method attributeGetter, Method attributeSetter) {
			this(displayName, attributeGetter);
			this.attributeSetter = attributeSetter;
		}

		public AEntityAttribute(String displayName, Method attributeGetter) {
			this.displayName = displayName;
			this.attributeGetter = attributeGetter;
		}

		public AEntityAttribute(AThymeleafModel<T>.EntityAttributeFactory factory) {
			this(factory.getDisplayName(), factory.getAttributeGetter(), factory.getAttributeSetter());
		}

		public Method getAttributeGetter() {
			return attributeGetter;
		}

		public Method getAttributeSetter() {
			return attributeSetter;
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

		public boolean isComplex() {
			return BaseEntity.class.isAssignableFrom(getType());
		}

		public boolean isCollection() {
			return Collection.class.isAssignableFrom(getType());
		}

		public boolean isEntityCollection() {
			return isCollection() && BaseEntity.class.isAssignableFrom(getCollectionType());
		}

		public boolean isPrimitiveCollection() {
			return isCollection() && (getCollectionType().isPrimitive() || String.class.isAssignableFrom(getCollectionType()));
		}

		public boolean isTextInputType() {
			if (getType().equals(String.class)) {
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

		public boolean isSimpleAttribute() {
			if (getType().equals(String.class)) {
				return true;
			}
			if (getType().isPrimitive()) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		public String getHTMLInput(T object) {

			if (isTextInputType()) {
				String simpleInput = "<input id=\"%1$s\" name=\"%1$s\" value=\"%2$s\">";
				return String.format(simpleInput, getAttributePath(), getHTMLValue(object));
			}

			// Collection nur anzeige
			if (Collection.class.isAssignableFrom(getType())) {
				return getHTMLValue(object);
			}

			if (getType().equals(boolean.class)) {
				String simpleInput = "<input type=\"checkbox\" class=\"check\" id=\"%1$s\" name=\"%1$s\" %2$s>";
				String checked = (boolean) getValueObject(object) ? "checked" : "";
				return String.format(simpleInput, getAttributePath(), checked);
			}

			if (BaseEntity.class.isAssignableFrom(getType())) {
				String select = "<select class=\"complexEntitySelect\" name=\"%1$s\" id=\"%1$s\">%2$s</select>";
				String option = "<option value=\"%1$s\" %3$s>%2$s</option>";
				String options = "<option value=\"0\">Leer</option>";
				for (BaseEntity e : service.getAll((Class<T>) getType())) {
					String selected = e.equals(getValueObject(object)) ? "selected" : "";
					options += String.format(option, e.getId(), e.getShortHTMLDisplay(), selected);
				}
				return String.format(select, getAttributePath(), options);
				// return "<select name=address id=address><option value=1>Test</option></select>";
			}

			return "Unknown Type: " + getType().getCanonicalName();
		}

		public String getHTMLValue(T object) {
			if (getAttributeGetter().isAnnotationPresent(Id.class)) {
				return LinkUtils.createIDLinkFor(object).createHTMLLink();
			}

			Object gotObject = null;
			try {
				gotObject = getAttributeGetter().invoke(object);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new IllegalStateException(e);
			}
			if (gotObject == null) {
				return NA_DISPLAY;
			}
			if (gotObject instanceof Collection<?>) {
				Collection<?> collection = (Collection<?>) gotObject;
				return "" + collection.size();
			}
			if (gotObject instanceof BaseEntity) {
				BaseEntity be = (BaseEntity) gotObject;
				return LinkUtils.createLinkFor(be).createHTMLLink();
			}
			return gotObject.toString();
		}

		public Class<?> getCollectionType() {
			ParameterizedType collectionType = (ParameterizedType) attributeGetter.getGenericReturnType();
			return (Class<?>) collectionType.getActualTypeArguments()[0];
		}

		public Object getValueObject(T object) {
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
}
