package concept.predefined.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(ENTITYVIEW_PATH + "{type}/{id}/edit")
	public <T extends BaseEntity> ModelAndView editEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		return entityView(type, id, true);
	}

	@PostMapping(ENTITYVIEW_PATH + "{type}/{id}/save")
	public <T extends BaseEntity> ModelAndView saveEntity(@PathVariable("type") String type, @PathVariable("id") int id) {
		// TODO form with T object als model um binding auszufüren, setter des alten objects dynamisch aufrufen mit gettern des neuen, benötigt anpassung in entityview
		return entityView(type, id, false);
	}

	private <T extends BaseEntity> ModelAndView entityView(String type, int id, boolean edit) {
		ModelAndView mv = new ModelAndView("entityview");
		Class<T> clazz = findByName(type);
		T entity = service.getByID(id, clazz);
		ThymeleafEntityModel<T> eModel = new ThymeleafEntityModel<>(entity);
		mv.addObject("entityModel", eModel);
		mv.addObject("edit", edit);
		return mv;
	}

	@GetMapping(TABLEVIEW_PATH + "{type}")
	public <T extends BaseEntity> ModelAndView tableTest(@PathVariable("type") String entity) {
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
}
