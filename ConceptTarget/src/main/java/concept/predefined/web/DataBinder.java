package concept.predefined.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;

@Component
public class DataBinder {

	@Autowired
	private EntityManager	em;
	@Autowired
	private DBService		service;
	@Autowired
	private EntityUtil		entityUtil;

	private BindingResult	bindingResult;

	public BaseEntity bindRequestToClass(String className, HttpServletRequest request) {

		BaseEntity object = entityUtil.createObject(className);
		ServletRequestDataBinder binder = getRequestDataBinder(object);

		binder.bind(request);
		bindingResult = binder.getBindingResult();

		// Debug:
		// request.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));
		// System.out.println(ReflectionToStringBuilder.toString(object, ReflectionToStringBuilder.getDefaultStyle(), true, true, Object.class));

		return object;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	private ServletRequestDataBinder getRequestDataBinder(BaseEntity object) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(object);
		for (EntityType<?> entityType : em.getMetamodel().getEntities()) {
			addEditorForEntityType(binder, entityType);
		}
		addDateEditor(binder);
		return binder;
	}

	private void addDateEditor(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text.isEmpty()) {
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat();
				try {
					setValue(sdf.parseObject(text));
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void addEditorForEntityType(ServletRequestDataBinder binder, EntityType<?> entityType) {
		Class<BaseEntity> clazz = (Class<BaseEntity>) entityType.getJavaType();
		binder.registerCustomEditor(clazz, createPropertyEditorSupport(clazz));
	}

	private PropertyEditorSupport createPropertyEditorSupport(Class<BaseEntity> clazz) {
		return new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Optional<BaseEntity> o = service.getByID(Integer.valueOf(text), clazz);
				try {
					setValue(o.orElse(clazz.newInstance()));
				} catch (InstantiationException | IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}
}
