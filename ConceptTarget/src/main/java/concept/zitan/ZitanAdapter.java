package concept.zitan;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AbstractObjects.DataObject;
import Attributes.Attribute;
import Attributes.ConceptAttribute;
import Attributes.DataAttribute;
import Concepts.IndividualConcept;
import Concepts.Scope;
import Concepts.SetConcept;
import ConcreteDomain.ConcreteType;
import ConcreteDomain.AtomicTypes.AbsoluteTimePoint;
import ConcreteDomain.AtomicTypes.BooleanObject;
import ConcreteDomain.AtomicTypes.FloatObject;
import ConcreteDomain.AtomicTypes.IntegerObject;
import ConcreteDomain.AtomicTypes.StringObject;
import MISC.Context;
import RuleEngine.AbstractProcessor;
import RuleEngine.HierarchyProcessors.DataAttributeFunctionalSingleDefinition;
import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.ExtendedEntity;
import concept.predefined.marker.ExtraAttributes;
import concept.predefined.marker.Invisible;
import concept.predefined.web.EntityUtil;

@RestController
@RequestMapping("zitan")
public class ZitanAdapter {

	private Logger															logger					= Logger.getLogger(ZitanAdapter.class.getName());

	@Autowired
	private EntityManager													em;
	@Autowired
	private DBService														dbService;
	@Autowired
	private EntityUtil			entityUtil;

	private Context															context;

	private Map<Class<? extends ExtendedEntity>, SetConcept>				setConcepts				= new HashMap<>();
	private Map<SetConcept, List<Attribute>>								setConceptAttributes	= new HashMap<>();

	private Map<Class<? extends ExtendedEntity>, List<IndividualConcept>>	indivConcepts			= new HashMap<>();

	private Map<String, Attribute>											extraAttributes			= new HashMap<>();

	private ConcreteType													absoluteTimePoint;

	@PostConstruct
	public void init() {
		logger.info("Initializing " + ZitanAdapter.class.getSimpleName());
		context = new Context("concept");

		absoluteTimePoint = new ConcreteType("AbsoluteTimePoint", "AbsoluteTimePoint", AbsoluteTimePoint.class, null, context); // TODO Internal Type?

		createSetConcepts();
		addClassHierarchy();
		createIndividualConcepts();
	}

	@SuppressWarnings("unchecked")
	private Stream<Class<? extends ExtendedEntity>> getJavaEntityClasses() {
		// @formatter:off
		return em.getMetamodel().getEntities().stream()
			.map(EntityType::getJavaType)
			.filter(e -> ExtendedEntity.class.isAssignableFrom(e))
			.map(e -> (Class<? extends ExtendedEntity>) e);
		// @formatter:on
	}

	private void createSetConcepts() {
		getJavaEntityClasses().forEach(e -> createSetConceptForClass(e));
	}

	private void createSetConceptForClass(Class<? extends ExtendedEntity> javaClass) {
		logger.info("Creating SetConcept for Class " + javaClass.getName());
		SetConcept setConcept = new SetConcept(javaClass.getSimpleName(), context);
		setConcepts.put(javaClass, setConcept);
		addAttributesToSetConcept(setConcept, javaClass);
	}

	private void addAttributesToSetConcept(SetConcept setConcept, Class<? extends ExtendedEntity> javaClass) {
		setConceptAttributes.put(setConcept, new ArrayList<>());
		getAttributeGetters(javaClass).forEach(attributeGetter -> addAttributeToSetConcept(setConcept, attributeGetter));
	}

	private void addAttributeToSetConcept(SetConcept setConcept, Method attributeGetter) {
		String attributeName = getAttributeName(attributeGetter);
		Class<?> attributeJavaType = attributeGetter.getReturnType();
		Attribute zitanAttribute = null;
		if (attributeJavaType.isPrimitive() || attributeJavaType.equals(String.class)) {
			ConcreteType dataType = getPrimitiveDataType(attributeJavaType.getSimpleName());
			zitanAttribute = new DataAttribute(attributeName, context, true, setConcept, dataType);
		} else if (ExtendedEntity.class.isAssignableFrom(attributeJavaType)) {
			SetConcept other = setConcepts.get(attributeJavaType);
			ConceptAttribute conceptAttribute = new ConceptAttribute(attributeName, context, true, setConcept, other);
			zitanAttribute = conceptAttribute;
		} else if (Collection.class.isAssignableFrom(attributeJavaType)) {
			// TODO Collections;
			if (isEntityCollection(attributeGetter)) {

			} else {

			}
		} else if (Date.class.isAssignableFrom(attributeJavaType)) {
			zitanAttribute = new DataAttribute(attributeName, context, true, setConcept, absoluteTimePoint);
		}

		if (zitanAttribute != null) {
			setConceptAttributes.get(setConcept).add(zitanAttribute);
		} else {
			logger.warning("Missing: " + attributeName + " of type " + attributeJavaType.getSimpleName());
		}
	}

	private boolean isEntityCollection(Method attributeGetter) {
		return ExtendedEntity.class.isAssignableFrom(getCollectionType(attributeGetter));
	}

	private static Class<?> getCollectionType(Method attributeGetter) {
		ParameterizedType collectionType = (ParameterizedType) attributeGetter.getGenericReturnType();
		return (Class<?>) collectionType.getActualTypeArguments()[0];
	}

	private ConcreteType getPrimitiveDataType(String primitive) {
		return (ConcreteType) Objects.requireNonNull(context.getDataType(changeJavaTypeToZitan(primitive)), primitive);
	}

	private static String changeJavaTypeToZitan(String javaType) {
		switch (javaType) {
		case "int":
			return "Integer";
		case "boolean":
			return "Boolean";
		case "double":
			return "Float";
		case "float":
			return "Float";
		case "char":
			return "Char";
		case "long":
			return "Integer";
		case "byte":
			return "Integer";
		case "short":
			return "Integer";
		default:
			return javaType;
		}
	}

	private static String getAttributeName(Method attributeGetter) {
		return attributeGetter.getName().substring(3, attributeGetter.getName().length());
	}

	private static Stream<Method> getAttributeGetters(Class<?> javaClass) {
		return Arrays.stream(javaClass.getMethods()).filter(ZitanAdapter::isAttributeGetter);
	}

	private void addClassHierarchy() {
		// TODO Auto-generated method stub
	}

	private void createIndividualConcepts() {
		getJavaEntityClasses().forEach(javaClass -> {
			indivConcepts.put(javaClass, new ArrayList<>());
			dbService.getAllWithoutChildren(javaClass).forEach(entity -> createIndividualConcept(entity));
			dbService.getAllWithoutChildren(javaClass).forEach(entity -> addIndividualConceptAttributes(entity));
		});
	}

	private void createIndividualConcept(ExtendedEntity entity) {
		IndividualConcept indivConcept = new IndividualConcept(getEntityId(entity), context);
		List<IndividualConcept> concepts = indivConcepts.get(entity.getClass());
		concepts.add(indivConcept);
	}

	private void addIndividualConceptAttributes(ExtendedEntity entity) {
		addAttributes(getIndividualConcept(entity), entity);
		addExtraAttributes(getIndividualConcept(entity), entity);
	}

	private void addExtraAttributes(IndividualConcept individualConcept, ExtendedEntity entity) {
		Map<concept.predefined.dynamic.Attribute, String> extraAttributes = entity.getExtraAttributes();
		extraAttributes.forEach((attr, value) -> {
			Attribute extraAttribute = getOrCreateExtraAttribute(attr, individualConcept, entity);
			StringObject so = new StringObject(value);
			individualConcept.add(extraAttribute, so, Scope.LOCAL, context, new StringBuilder());
		});
	}

	private Attribute getOrCreateExtraAttribute(concept.predefined.dynamic.Attribute attr, IndividualConcept individualConcept, ExtendedEntity entity) {
		if (extraAttributes.containsKey(attr.getName())) {
			return extraAttributes.get(attr.getName());
		}
		ConcreteType dataType = (ConcreteType) context.getDataType("String");
		DataAttribute dataAttribute = new DataAttribute(attr.getName(), context, true, setConcepts.get(entity.getClass()), dataType); // TODO Domain nur SetConcept? was ist mit Attributen, die nur ein individualconcept haben?
		extraAttributes.put(attr.getName(), dataAttribute);
		return dataAttribute;
	}

	private IndividualConcept getIndividualConcept(ExtendedEntity entity) {
		// @formatter:off
		return indivConcepts.get(entity.getClass()).stream()
				.filter(indConcept -> indConcept.getName().equals(getEntityId(entity)))
				.findAny().get();
		// @formatter:on
	}

	private String getEntityId(ExtendedEntity entity) {
		return entity.getClass().getSimpleName() + entity.getId();
	}

	private void addAttributes(IndividualConcept indivConcept, ExtendedEntity entity) {
		getAttributeGetters(entity.getClass()).forEach(getter -> addAttributeToIndividualConcept(indivConcept, entity, getter));
	}

	private void addAttributeToIndividualConcept(IndividualConcept indivConcept, ExtendedEntity entity, Method attributeGetter) {
		Attribute attribute = getConceptAttribute(getAttributeName(attributeGetter), setConcepts.get(entity.getClass()));
		DataObject data = null;
		try {
			data = getDataObject(entity, attributeGetter, attribute);
		} catch (Exception e) {
			throw new IllegalStateException("Can't read " + attributeGetter + " of " + entity, e);
		}
		if (data != null) {
			indivConcept.add(attribute, data, Scope.LOCAL, context, new StringBuilder());
		}
	}

	private DataObject getDataObject(ExtendedEntity entity, Method attributeGetter, Attribute attribute) throws Exception {
		Object value = attributeGetter.invoke(entity);
		if (value == null) {
			return null;
		}
		Class<?> javaType = attributeGetter.getReturnType();
		if (javaType.equals(String.class)) {
			return new StringObject((String) value);
		}
		if (javaType.equals(int.class)) {
			return new IntegerObject((int) value);
		}
		if (javaType.equals(double.class) || javaType.equals(float.class)) {
			return new FloatObject((float) value);
		}
		if (javaType.equals(boolean.class)) {
			return new BooleanObject((boolean) value);
		}

		if (javaType.equals(Date.class)) {
			Calendar c = Calendar.getInstance();
			c.setTime((Date) value);
			return new AbsoluteTimePoint(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		}

		if (ExtendedEntity.class.isAssignableFrom(javaType)) {
			return getIndividualConcept(entity);
		}

		if (Collection.class.isAssignableFrom(javaType)) {
			// TODO Collections;
			if (isEntityCollection(attributeGetter)) {

			} else {

			}
		}
		logger.warning("Missing DataValue: " + entity + " " + attributeGetter);
		return null;
	}

	private Attribute getConceptAttribute(String attributeName, SetConcept setConcept) {
		// @formatter:off
		return setConceptAttributes.get(setConcept).stream()
				.filter(attribute -> attribute.getName().equals(attributeName))
				.findAny().orElse(null);
		// @formatter:on
	}

	private static boolean isAttributeGetter(Method method) {
		if (method.getParameterCount() != 0) {
			return false;
		}
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getReturnType().equals(Void.class)) {
			return false;
		}
		if (method.getName().equals("getClass")) {
			return false;
		}
		if (method.isAnnotationPresent(Invisible.class)) {
			return false;
		}
		if (method.isAnnotationPresent(ExtraAttributes.class)) {
			return false;
		}
		return true;
	}

	@RequestMapping("/context")
	public Context context() {
		return context;
	}

	@RequestMapping("/setconcepts")
	public Map<Class<? extends ExtendedEntity>, SetConcept> setConcepts() {
		return setConcepts;
	}

	@RequestMapping("/indivConcepts")
	public Map<Class<? extends ExtendedEntity>, List<IndividualConcept>> indivConcepts() {
		return indivConcepts;
	}

	@RequestMapping("/setconceptattributes")
	public List<Attribute> setConceptAttributes(@RequestParam String conceptName) {
		for (SetConcept setConcept : setConceptAttributes.keySet()) {
			if (setConcept.getName().equalsIgnoreCase(conceptName)) {
				return setConceptAttributes.get(setConcept);
			}
		}
		throw new IllegalArgumentException(conceptName + " not found");
	}

	@RequestMapping("/abfrage")
	public Object abfrage(String concept, String attributeName) {
		Stream<Object[]> input = getEntitiesAsQueryInput(entityUtil.findClassByName(concept));
//		ConceptAttributeFunctionalDefinition def = new ConceptAttributeFunctionalDefinition("Simple Access");
		DataAttributeFunctionalSingleDefinition def = new DataAttributeFunctionalSingleDefinition("Simple Access");
		Attribute attribute = getAttribute(concept, attributeName);
		def.setOutputIndex(1).setAttribute(attribute).setConceptIndex(0);
		AbstractProcessor processor = def.makeProcessor(context, null);
		processor.setInputStream(input);
		Stream<Object[]> output = processor.getOutputStream();
		return output.collect(Collectors.toList());
	}
	
	public Stream<Object[]> getEntitiesAsQueryInput(Class<? extends BaseEntity> clazz) {
		return indivConcepts.get(clazz).stream().map(o -> new Object[] {o,null});
	}
	
	public Attribute getAttribute(String conceptName, String attributeName) {
		List<Attribute> setConceptAttributes = setConceptAttributes(conceptName);
		for (Attribute attribute : setConceptAttributes) {
			if(attribute.getName().equalsIgnoreCase(attributeName)) {
				return attribute;
			}
		}
		throw new IllegalArgumentException(attributeName + " not found in " + conceptName);
	}

	private Set<EntityType<?>> getEntities() {
		return em.getMetamodel().getEntities();
	}

	@RequestMapping("/entities")
	public String entitiesString() {
		return getEntities().toString();
	}
}
