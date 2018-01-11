package de.xenocraft.exp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vorlesung implements IdAble{

	private int				id;
	private String			name;
	private Dozent			dozent;
	private List<Student>	studenten;

	public Vorlesung() {

	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "dozent")
	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	@ManyToMany
	@JoinTable(name = "student_vorlesung", joinColumns = { @JoinColumn(name = "vorlesung") }, inverseJoinColumns = { @JoinColumn(name = "student") })
	public List<Student> getStudenten() {
		return studenten;
	}

	public void setStudenten(List<Student> studenten) {
		this.studenten = studenten;
	}

}
