package concept.predefined.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Id;

import concept.predefined.BaseEntity;

public abstract class AThymeleafModel<T extends BaseEntity> {

	private EntityAttribute			idAttribute;
	private List<EntityAttribute>	attributes;
	protected Class<T>				targetClass;
	private String					targetName;

	public AThymeleafModel(Class<T> clazz) {
		attributes = new ArrayList<>();
		targetClass = clazz;
		generateAttributes();
	}

	private void generateAttributes() {
		targetName = targetClass.getSimpleName();
		for (Method m : targetClass.getMethods()) {
			if (m.getParameterCount() != 0) {
				continue; // Getter haben keine Argumente
			}
			if (!m.getName().startsWith("get")) {
				continue; // Getter fangen mit get an
			}

			String name = m.getName().substring(3, m.getName().length());
			EntityAttribute col = new EntityAttribute(name, m, findSetter(m));
			if (name.equals("Class")) {
				continue; // Class uninteressant
			}
			if (name.equalsIgnoreCase("Id")) {
				idAttribute = col;
				continue; // Id ist extra
			}
			addAttribute(col);
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

	public EntityAttribute getIdAttribute() {
		return idAttribute;
	}

	public void addAttribute(EntityAttribute attribute) {
		Objects.requireNonNull(attribute);
		attributes.add(attribute);
		Collections.sort(attributes);
	}

	public List<EntityAttribute> getAttributes() {
		return attributes;
	}

	public static String firstLetterCap(String input) {
		if (input == null) {
			return null;
		}
		String output = input.substring(0, 1).toUpperCase();
		if (input.length() > 1) {
			output += input.substring(1);
		}
		return output;
	}

	public class EntityAttribute implements Comparable<EntityAttribute> {
		private String	displayName;
		private Method	attributeGetter;
		private Method	attributeSetter;

		public EntityAttribute(String displayName, Method attributeGetter, Method attributeSetter) {
			this(displayName, attributeGetter);
			this.attributeSetter = attributeSetter;
		}

		public EntityAttribute(String displayName, Method attributeGetter) {
			this.displayName = displayName;
			this.attributeGetter = attributeGetter;
		}

		public boolean isReadOnly() {
			return attributeSetter == null;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public Method getAttributeGetter() {
			return attributeGetter;
		}

		public Method getAttributeSetter() {
			return attributeSetter;
		}

		public String getValue(T object) {
			if (attributeGetter.isAnnotationPresent(Id.class)) {
				return LinkUtils.createIDLinkFor(object).createHTMLLink();
			}

			Object gotObject = null;
			try {
				gotObject = attributeGetter.invoke(object);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			if (gotObject == null) {
				return "N/A";
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

		public Class<?> getType() {
			return attributeGetter.getReturnType();
		}

		public String getInput(T object) {
			Class<?> type = getType();
			if (type.equals(String.class)) {
				return "<input type=\"text\" value=\"" + getValue(object) + "\">";
			}
			return "Unknown Type: " + type.getCanonicalName();
		}

		@Override
		public int compareTo(EntityAttribute o) {
			return this.displayName.compareTo(o.displayName);
		}
	}
}
