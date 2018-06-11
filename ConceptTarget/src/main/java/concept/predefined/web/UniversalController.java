package concept.predefined.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import concept.predefined.BaseEntity;
import concept.predefined.DBService;
import concept.predefined.dynamic.Attribute;
import concept.predefined.web.LinkUtils.Link;
import concept.predefined.web.model.ThymeleafEntityModel;
import concept.predefined.web.model.ThymeleafTableModel;

@Controller
public class UniversalController {

	public static final String	TABLEVIEW_PATH	= "/entities/";

	public static final String	ENTITYVIEW_PATH	= "/entity/";

	@Autowired
	private DataBinder			dataBinder;

	@Autowired
	private DBService			service;
	@Autowired
	private EntityManager		em;
	@Autowired
	private EntityUtil			entityUtil;

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
	public ModelAndView viewEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		return entityView(type, id, false);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/new")
	public ModelAndView newEntity(@PathVariable("type") String type) {
		return entityView(type, 0, true);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/edit")
	public ModelAndView editEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		return entityView(type, id, true);
	}

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/delete")
	public String deleteEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		Class<BaseEntity> clazz = entityUtil.findClassByName(type);
		Optional<BaseEntity> entity = service.getByID(id, clazz);
		if (entity.isPresent()) {
			service.delete(entity.get());
		} else {
			throw new IllegalArgumentException("No " + type + " with id " + id);
		}
		return "redirect:" + TABLEVIEW_PATH + type;
	}

	@PostMapping(ENTITYVIEW_PATH + "{type}/{id}/save")
	public Object saveEntity(@PathVariable("type") String type, @PathVariable("id") int id, HttpServletRequest srequest) throws InstantiationException, IllegalAccessException {
		BaseEntity entity = dataBinder.bindRequestToClass(type, srequest);

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

	private ModelAndView entityView(String type, int id, boolean edit) {
		Class<BaseEntity> clazz = entityUtil.findClassByName(type);
		BaseEntity entity = null;
		if (id != 0) {
			entity = service.getByID(id, clazz).get();
		} else {
			entity = entityUtil.createObject(type);
			try {
				entity = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
		}
		return showEntity(entity, edit);
	}

	@GetMapping(TABLEVIEW_PATH + "{type}")
	public ModelAndView showEntitiesTable(@PathVariable("type") String entity) {
		ModelAndView mv = new ModelAndView("tableview");
		Class<BaseEntity> clazz = entityUtil.findClassByName(entity);
		ThymeleafTableModel<BaseEntity> ttm = new ThymeleafTableModel<>(clazz);
		List<BaseEntity> objects = service.getAll(clazz);
		ttm.setObjects(objects);
		mv.addObject("tablemodel", ttm);
		return mv;
	}

	@ModelAttribute("menuitems")
	public List<Link> getMenuItems() {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		List<Link> links = new ArrayList<>();
		for (EntityType<?> entity : entities) {
			Class<?> javaType = entity.getJavaType();
			String name = javaType.getSimpleName();
			String link = TABLEVIEW_PATH + name;
			links.add(new Link(name, link));
		}
		Collections.sort(links);
		return links;
	}

	private ModelAndView showEntity(BaseEntity entity, boolean edit) {
		ModelAndView mv = new ModelAndView("entityview");
		ThymeleafEntityModel<BaseEntity> eModel = new ThymeleafEntityModel<>(entity, service);
		mv.addObject("entityModel", eModel);
		mv.addObject("edit", edit);
		mv.addObject("allExtraAttributes", getExtraAttributesSorted());
		return mv;
	}

	private List<Attribute> getExtraAttributesSorted() {
		List<Attribute> all = service.getAll(Attribute.class);
		Collections.sort(all);
		return all;
	}

}
