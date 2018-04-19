package concept.generated.model.base;

import java.util.List;
import javax.persistence.*;
import concept.predefined.BaseEntity;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class PersonBase extends BaseEntity{

	private String vorname;
	private String nachname;
	private List<Integer> noten;



	public String getVorname(){
		return vorname;
	}

	public void setVorname(String vorname){
		this.vorname = vorname;
	}

	public String getNachname(){
		return nachname;
	}

	public void setNachname(String nachname){
		this.nachname = nachname;
	}

	@ElementCollection
	public List<Integer> getNoten(){
		return noten;
	}

	public void setNoten(List<Integer> noten){
		this.noten = noten;
	}

	@Transient
	public String getName(){
		return vorname+" "+nachname;
	}

	@Transient
	public int getAlter(){
		return 5-3;
	}

}