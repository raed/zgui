package de.xenocraft.zgui.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Dozent extends Person {

	private String			fachbereich;
	private List<Vorlesung>	vorlesungen;

	public Dozent() {

	}

	public String getFachbereich() {
		return fachbereich;
	}

	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}

	@OneToMany(mappedBy = "dozent")
	public List<Vorlesung> getVorlesungen() {
		return vorlesungen;
	}

	public void setVorlesungen(List<Vorlesung> vorlesungen) {
		this.vorlesungen = vorlesungen;
	}

	@Override
	public String toString() {
		return String.format("Dozent(fachbereich=%s,parent=%s)", fachbereich, super.toString());
	}

}
