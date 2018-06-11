package concept.generated.model.base;

import java.util.*;
import javax.persistence.*;
import concept.generated.model.Vorlesung;
import concept.generated.model.Person;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class DozentBase extends Person{

	private List<Vorlesung> vorlesungen = new ArrayList<Vorlesung>();

	@OneToMany(mappedBy = "dozent")
	public List<Vorlesung> getVorlesungen(){
		return vorlesungen;
	}

	public void setVorlesungen(List<Vorlesung> vorlesungen){
		this.vorlesungen = vorlesungen;
	}

}