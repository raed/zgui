package concept.predefined.web.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.marker.Invisible;
import concept.predefined.web.LinkUtils;
import concept.predefined.web.LinkUtils.Link;
import concept.predefined.web.model.attributes.AEntityAttribute;
import concept.predefined.web.model.attributes.ComplexEntityAttribute;
import concept.predefined.web.model.attributes.EntityAttributeFactory;
import concept.predefined.web.model.attributes.EntityCollectionAttribute;
import concept.predefined.web.model.attributes.ExtraEntityAttributes;
import concept.predefined.web.model.attributes.SimpleCollectionAttribute;

public abstract class AThymeleafModel<T extends BaseEntity> {

	protected AEntityAttribute			idAttribute;
	protected List<AEntityAttribute>	attributes;
	protected ExtraEntityAttributes		extraAttributes;

	protected Class<T>					targetClass;
	protected String					targetName;
	private String						parents	= "";

	protected DBService					service;

	public AThymeleafModel(Class<T> clazz, DBService service) {
		this.attributes = new ArrayList<>();
		this.targetClass = clazz;
		this.service = service;
		generateAttributes();
	}

	@Override
	public String toString() {
		return "AThymeleafModel for " + targetClass + " target: " + targetName;
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

			EntityAttributeFactory eaf = new EntityAttributeFactory(name, m, findSetter(m), service, parents);
			AEntityAttribute attr = eaf.createAttribute();
			if (name.equalsIgnoreCase("Id")) {
				idAttribute = attr;
				continue; // Id ist extra
			}
			if (attr instanceof ExtraEntityAttributes) {
				extraAttributes = (ExtraEntityAttributes) attr;
				continue; // Extra Attributes sind extra
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

	public void addAttribute(AEntityAttribute attr) {
		Objects.requireNonNull(attr);
		attributes.add(attr);
		Collections.sort(attributes);
	}

	public List<AEntityAttribute> getAttributes() {
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public <A extends AEntityAttribute> List<A> getAttributes(Class<A> type) {
		// @formatter:off
		return attributes.stream()
				.filter(e -> e.getClass().equals(type))
				.map(e -> (A)e)
				.collect(Collectors.toList());
		// @formatter:on
	}

	public List<ComplexEntityAttribute> getComplexAttributes() {
		return getAttributes(ComplexEntityAttribute.class);
	}

	public List<EntityCollectionAttribute> getEntityCollectionAttributes() {
		return getAttributes(EntityCollectionAttribute.class);
	}

	public List<SimpleCollectionAttribute> getSimpleCollectionAttributes() {
		return getAttributes(SimpleCollectionAttribute.class);
	}

	public Optional<ExtraEntityAttributes> getExtraEntityAttributes() {
		return Optional.ofNullable(extraAttributes);
	}

	public Link getNewLink() {
		return LinkUtils.createAddLinkFor(targetClass);
	}

	public void setParent(String parentName) {
		parents += parentName + ".";
		attributes.forEach(a -> a.setParent(parents));
		idAttribute.setParent(parents);
	}

}
