package de.xenocraft.zgui.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import de.xenocraft.zgui.model.Person;

@Controller
@ControllerAdvice
@RequestMapping("/person")
public class PersonController extends AEntityController<Person> {

	public PersonController() {
		super("person", "personen");
	}

	@Override
	protected Class<Person> getEntityClass() {
		return Person.class;
	}

	@Override
	protected void addAdditionalObjects(Map<String, Object> additionals) {

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}
}
