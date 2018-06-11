package concept.generated.model.base;

import java.util.*;
import javax.persistence.*;
import concept.predefined.ExtendedEntity;
import concept.generated.model.Address;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class PersonBase extends ExtendedEntity{

	private String vorname;
	private String nachname;
	private Address address = new Address();
	private Date geburtstag;



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

	@ManyToOne(cascade = CascadeType.ALL)
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Date getGeburtstag(){
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag){
		this.geburtstag = geburtstag;
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