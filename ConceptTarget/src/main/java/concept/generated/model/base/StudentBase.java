package concept.generated.model.base;

import java.util.*;
import javax.persistence.*;
import concept.generated.model.Vorlesung;
import concept.generated.model.Person;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class StudentBase extends Person{

	private int matrikelnummer;
	private List<Vorlesung> vorlesungen = new ArrayList<Vorlesung>();
	private String fach;
	private boolean master;
	private List<Integer> noten = new ArrayList<Integer>();

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

	public String getFach(){
		return fach;
	}

	public void setFach(String fach){
		this.fach = fach;
	}

	public boolean getMaster(){
		return master;
	}

	public void setMaster(boolean master){
		this.master = master;
	}

	@ElementCollection
	public List<Integer> getNoten(){
		return noten;
	}

	public void setNoten(List<Integer> noten){
		this.noten = noten;
	}

}