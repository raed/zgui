package de.xenocraft.zgui.model;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Student extends Person {

	private int				matrikelnummer;
	private Mensakarte		mensakarte;
	private List<Vorlesung>	vorlesungen;

	public Student() {

	}

	public int getMatrikelnummer() {
		return matrikelnummer;
	}

	public void setMatrikelnummer(int matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}

	@Convert(converter = Mensakarte.MensakarteConverter.class)
	public Mensakarte getMensakarte() {
		return mensakarte;
	}

	public void setMensakarte(Mensakarte mensakarte) {
		this.mensakarte = mensakarte;
	}

	@ManyToMany
	@JoinTable(name = "student_vorlesung", joinColumns = { @JoinColumn(name = "student") }, inverseJoinColumns = { @JoinColumn(name = "vorlesung") })
	public List<Vorlesung> getVorlesungen() {
		return vorlesungen;
	}

	public void setVorlesungen(List<Vorlesung> vorlesungen) {
		this.vorlesungen = vorlesungen;
	}

	@Override
	public String toString() {
		return String.format("Student(matrikelnummer=%d, mensakarte=%s,parent=%s)", matrikelnummer, mensakarte, super.toString());
	}

}
