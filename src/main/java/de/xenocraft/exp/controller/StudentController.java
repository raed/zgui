package de.xenocraft.exp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import de.xenocraft.exp.model.Student;
import de.xenocraft.exp.model.Vorlesung;

@Controller
@ControllerAdvice
@RequestMapping("/student")
public class StudentController extends AEntityController<Student> {

	public StudentController() {
		super("student", "studenten");
	}

	@Override
	protected Class<Student> getEntityClass() {
		return Student.class;
	}

	@Override
	protected void addAdditionalObjects(Map<String, Object> additionals) {
		additionals.put("allVorlesungen", dao.getAll(Vorlesung.class));
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Vorlesung.class, new SimplePropertyEditorSupporter(Vorlesung.class));
	}
}
