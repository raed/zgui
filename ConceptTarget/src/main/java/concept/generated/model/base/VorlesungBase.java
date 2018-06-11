package concept.generated.model.base;

import java.util.*;
import javax.persistence.*;
import concept.predefined.ExtendedEntity;
import concept.generated.model.Dozent;
import concept.generated.model.Student;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class VorlesungBase extends ExtendedEntity{

	private String name;
	private Dozent dozent = new Dozent();
	private List<Student> studenten = new ArrayList<Student>();
	private List<Student> betreuer = new ArrayList<Student>();
	private Date ersterTermin;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Dozent getDozent(){
		return dozent;
	}

	public void setDozent(Dozent dozent){
		this.dozent = dozent;
	}

	@ManyToMany
	@JoinTable(name = "student_vorlesungen_vorlesung_studenten", joinColumns = { @JoinColumn(name = "vorlesung") }, inverseJoinColumns = { @JoinColumn(name = "student") })
	public List<Student> getStudenten(){
		return studenten;
	}

	public void setStudenten(List<Student> studenten){
		this.studenten = studenten;
	}

	@ManyToMany
	@JoinTable(name = "student_vorlesung_betreuer", joinColumns = { @JoinColumn(name = "vorlesung") }, inverseJoinColumns = { @JoinColumn(name = "student") })
	public List<Student> getBetreuer(){
		return betreuer;
	}

	public void setBetreuer(List<Student> betreuer){
		this.betreuer = betreuer;
	}

	public Date getErsterTermin(){
		return ersterTermin;
	}

	public void setErsterTermin(Date ersterTermin){
		this.ersterTermin = ersterTermin;
	}

}