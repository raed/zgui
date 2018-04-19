package concept.generated.model.base;

import java.util.List;
import javax.persistence.*;
import concept.generated.model.Address;
import concept.generated.model.Vorlesung;
import concept.generated.model.Person;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class StudentBase extends Person{

	private int matrikelnummer;
	private List<Vorlesung> vorlesungen;
	private Address address;
	private String fach;

	public int getMatrikelnummer(){
		return matrikelnummer;
	}

	public void setMatrikelnummer(int matrikelnummer){
		this.matrikelnummer = matrikelnummer;
	}

	@ManyToMany
	@JoinTable(name = "student_vorlesungen_vorlesung_studenten", joinColumns = { @JoinColumn(name = "student") }, inverseJoinColumns = { @JoinColumn(name = "vorlesung") })
	public List<Vorlesung> getVorlesungen(){
		return vorlesungen;
	}

	public void setVorlesungen(List<Vorlesung> vorlesungen){
		this.vorlesungen = vorlesungen;
	}

	@ManyToOne
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public String getFach(){
		return fach;
	}

	public void setFach(String fach){
		this.fach = fach;
	}

}