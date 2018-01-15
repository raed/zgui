package de.xenocraft.zgui.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import de.xenocraft.zgui.model.Dozent;

@Controller
@ControllerAdvice
@RequestMapping("/dozent")
public class DozentController extends AEntityController<Dozent> {

	public DozentController() {
		super("dozent", "dozenten");
	}
	
	@Override
	protected Class<Dozent> getEntityClass() {
		return Dozent.class;
	}

	@Override
	protected void addAdditionalObjects(Map<String, Object> additionals) {

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}
}
