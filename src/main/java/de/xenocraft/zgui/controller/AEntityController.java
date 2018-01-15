package de.xenocraft.zgui.controller;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.xenocraft.zgui.DAO;
import de.xenocraft.zgui.model.IdAble;

@Controller
public abstract class AEntityController<T extends IdAble> {

	private Class<T>	CLASS	= getEntityClass();

	@Autowired
	protected DAO		dao;

	private String		nameSingular;
	private String		namePlural;

	public AEntityController(String nameSingular, String namePlural) {
		this.nameSingular = nameSingular;
		this.namePlural = namePlural;
	}

	@GetMapping({ "/show", "" })
	public ModelAndView showEntity(@RequestParam(required = false, defaultValue = "0") int id) {
		if (id != 0) {
			T t = dao.getByID(id, CLASS);
			return showEntity(t, false);
		} else {
			ModelAndView mv = new ModelAndView(namePlural);
			mv.addObject(namePlural, dao.getAll(CLASS));
			return mv;
		}
	}

	@RequestMapping("/edit")
	public Object editEntity(@RequestParam(required = false, defaultValue = "0") int id, @Valid T t, String action, BindingResult binding) {
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			return showEntity(t, true);
		}
		if (action != null) {
			if (action.equals("save")) {
				t = dao.saveOrUpdate(t);
				return redirectToEntity(t);
			}
			if (action.equals("delete")) {
				dao.delete(dao.getByID(id, CLASS));
				return "redirect:/" + nameSingular + "/show";
			}
			if (action.equals("abort")) {
				return redirectToEntity(t);
			}
		}

		// edit:
		T s = dao.getByID(id, CLASS);
		if (s == null) {
			try {
				s = getEntityClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return showEntity(s, true);
	}

	private ModelAndView showEntity(@Valid T t, boolean edit) {
		ModelAndView mv = new ModelAndView(nameSingular);
		mv.addObject(nameSingular, t);
		mv.addObject("edit", edit);

		Map<String, Object> additionals = new HashMap<>();
		addAdditionalObjects(additionals);
		mv.addAllObjects(additionals);

		return mv;
	}

	private String redirectToEntity(T t) {
		return "redirect:/" + nameSingular + "/show?id=" + t.getId();
	}

	protected abstract Class<T> getEntityClass();

	protected void addAdditionalObjects(Map<String, Object> additionals) {

	}

	protected class SimplePropertyEditorSupporter extends PropertyEditorSupport {

		private Class<?> ECLASS;

		public SimplePropertyEditorSupporter(Class<?> ECLASS) {
			this.ECLASS = ECLASS;
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null || text == "") {
				setValue(null);
			} else {
				setValue(dao.getByID(Integer.valueOf(text).intValue(), ECLASS));
			}
		}

		@Override
		public String getAsText() {
			if (getValue() == null) {
				return null;
			}
			return "" + ((IdAble) getValue()).getId();
		}

	}

}
