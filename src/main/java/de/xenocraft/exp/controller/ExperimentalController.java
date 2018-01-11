package de.xenocraft.exp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.xenocraft.exp.DAO;

@Controller
public class ExperimentalController {

	@Autowired
	private DAO				dao;

	@Autowired
	private EntityManager	em;

	@GetMapping("/")
	public ModelAndView home() {
		List<EntityInfo> entityinfos = new ArrayList<>();
		em.getMetamodel().getEntities().forEach(e -> {
			EntityInfo info = new EntityInfo();
			info.classname = e.getJavaType().getSimpleName();
			info.entitycount = dao.getAll(e.getJavaType()).size();
			entityinfos.add(info);
		});
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("entityinfos", entityinfos);
		return mv;
	}

	public static class EntityInfo {
		public String	classname;
		public int		entitycount;
	}

}
