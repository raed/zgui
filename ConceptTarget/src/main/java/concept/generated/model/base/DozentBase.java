package concept.generated.model.base;

import java.util.List;
import javax.persistence.*;
import concept.generated.model.Address;
import concept.generated.model.Vorlesung;
import concept.generated.model.Person;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class DozentBase extends Person{

	private List<Vorlesung> vorlesungen;
	private Address address;

	@OneToMany(mappedBy = "dozent")
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

}