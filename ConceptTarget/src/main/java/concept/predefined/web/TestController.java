package concept.predefined.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.web.LinkUtils.Link;

@Controller
public class TestController {

	public static final String	TABLEVIEW_PATH	= "/entities/";

	public static final String	ENTITYVIEW_PATH	= "/entity/";

	@Autowired
	private DataBinder			dataBinder;

	@Autowired
	private DBService			service;
	@Autowired
	private EntityManager		em;

	@GetMapping("")
	public String home() {
		return "home";
	}

	@GetMapping("/em")
	@ResponseBody
	public String em() {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		StringBuilder sb = new StringBuilder();
		for (EntityType<?> entity : entities) {
			sb.append(entity.getName() + "\n");
		}
		return sb.toString();
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}")
	public <T extends BaseEntity> ModelAndView viewEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		return entityView(type, id, false);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/new")
	public <T extends BaseEntity> ModelAndView newEntity(@PathVariable("type") String type) {
		return entityView(type, 0, true);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/edit")
	public <T extends BaseEntity> ModelAndView editEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		return entityView(type, id, true);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/delete")
	public <T extends BaseEntity> String deleteEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		Class<T> clazz = findByName(type);
		Optional<T> entity = service.getByID(id, clazz);
		if (entity.isPresent()) {
			service.delete(entity.get());
		} else {
			throw new IllegalArgumentException("No " + type + " with id " + id);
		}
		return "redirect:" + TABLEVIEW_PATH + type;
	}

	@PostMapping(ENTITYVIEW_PATH + "{type}/{id}/save")
	public <T extends BaseEntity> Object saveEntity(@PathVariable("type") String type, @PathVariable("id") int id, HttpServletRequest srequest) throws InstantiationException, IllegalAccessException {
		BaseEntity entity = dataBinder.bindToClass(type, srequest);

		BindingResult br = dataBinder.getBindingResult();
		if (br.hasErrors()) {
			ModelAndView mv = showEntity(entity, true);
			mv.addObject("bindingResult", br);
			return mv;
		} else {
			entity = service.saveOrUpdate(entity);
		}

		return "redirect:" + ENTITYVIEW_PATH + type + "/" + entity.getId();
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/table")
	public ModelAndView getDetailTable(@PathVariable("type") String type, @PathVariable("id") int id, @RequestParam(name = "edit", defaultValue = "false") boolean edit) {
		ModelAndView mv = entityView(type, id, edit);
		mv.setViewName("entityview :: detailtable");
		@SuppressWarnings("unchecked")
		ThymeleafEntityModel<BaseEntity> eModel = (ThymeleafEntityModel<BaseEntity>) mv.getModel().get("entityModel");
		eModel.setParent(type);
		return mv;
	}

	private <T extends BaseEntity> ModelAndView entityView(String type, int id, boolean edit) {
		Class<T> clazz = findByName(type);
		BaseEntity entity = null;
		if (id != 0) {
			entity = service.getByID(id, clazz).get();
		} else {
			try {
				entity = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
		}
		return showEntity(entity, edit);
	}

	private ModelAndView showEntity(BaseEntity entity, boolean edit) {
		ModelAndView mv = new ModelAndView("entityview");
		ThymeleafEntityModel<BaseEntity> eModel = new ThymeleafEntityModel<>(entity, service);
		mv.addObject("entityModel", eModel);
		mv.addObject("edit", edit);
		return mv;
	}

	@GetMapping(TABLEVIEW_PATH + "{type}")
	public <T extends BaseEntity> ModelAndView showEntitiesTable(@PathVariable("type") String entity) {
		ModelAndView mv = new ModelAndView("tableview");
		Class<T> clazz = findByName(entity);
		ThymeleafTableModel<T> ttm = new ThymeleafTableModel<>(clazz);
		List<T> objects = service.getAll(clazz);
		ttm.setObjects(objects);
		mv.addObject("tablemodel", ttm);
		return mv;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> Class<T> findByName(String name) {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		for (EntityType<?> entity : entities) {
			Class<?> clazz = entity.getJavaType();
			if (clazz.getSimpleName().equalsIgnoreCase(name)) {
				return (Class<T>) clazz;
			}
		}
		throw new IllegalArgumentException("Class " + name + " is not found");
	}

	@ModelAttribute("menuitems")
	public List<Link> getMenuItems() {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		List<Link> links = new ArrayList<>();
		for (EntityType<?> entity : entities) {
			String name = entity.getJavaType().getSimpleName();
			String link = TABLEVIEW_PATH + name;
			links.add(new Link(name, link));
		}
		Collections.sort(links);
		return links;
	}

	@Component
	public class DataBinder {

		private BindingResult bindingResult;

		public BaseEntity bindToClass(String className, HttpServletRequest request) {
			BaseEntity object;
			try {
				object = (BaseEntity) findByName(className).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(e);
			}

			ServletRequestDataBinder binder = new ServletRequestDataBinder(object);

			for (EntityType<?> entityType : em.getMetamodel().getEntities()) {
				@SuppressWarnings("unchecked")
				Class<BaseEntity> clazz = (Class<BaseEntity>) entityType.getJavaType();
				binder.registerCustomEditor(clazz, new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						Optional<BaseEntity> o = service.getByID(Integer.valueOf(text), clazz);
						if (!o.isPresent()) {
							throw new IllegalArgumentException();
						}
						setValue(o.get());
					}
				});
			}

			binder.bind(request);
			bindingResult = binder.getBindingResult();

			// Debug:
			request.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));
			System.out.println(ReflectionToStringBuilder.toString(object, ReflectionToStringBuilder.getDefaultStyle(), true, true, Object.class));

			return object;
		}

		public BindingResult getBindingResult() {
			return bindingResult;
		}
	}
}
