package concept.generated.model.base;

import java.util.List;
import javax.persistence.*;
import concept.predefined.BaseEntity;
import concept.generated.model.Dozent;
import concept.generated.model.Student;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class VorlesungBase extends BaseEntity{

	private int id;
	private String name;
	private Dozent dozent;
	private List<Student> studenten;
	private List<Student> betreuer;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@ManyToOne
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

}