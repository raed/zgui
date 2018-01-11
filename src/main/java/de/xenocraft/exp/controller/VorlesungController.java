package de.xenocraft.exp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import de.xenocraft.exp.model.Dozent;
import de.xenocraft.exp.model.Student;
import de.xenocraft.exp.model.Vorlesung;

@Controller
@ControllerAdvice
@RequestMapping("/vorlesung")
public class VorlesungController extends AEntityController<Vorlesung> {

	public VorlesungController() {
		super("vorlesung", "vorlesungen");
	}

	@Override
	protected Class<Vorlesung> getEntityClass() {
		return Vorlesung.class;
	}

	@Override
	protected void addAdditionalObjects(Map<String, Object> additionals) {
		additionals.put("allDozenten", dao.getAll(Dozent.class));
		additionals.put("allStudenten", dao.getAll(Student.class));
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Dozent.class, new SimplePropertyEditorSupporter(Dozent.class));
		binder.registerCustomEditor(Student.class, new SimplePropertyEditorSupporter(Student.class));
	}
}
