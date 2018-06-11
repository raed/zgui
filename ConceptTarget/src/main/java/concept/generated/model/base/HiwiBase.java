package concept.generated.model.base;

import javax.persistence.*;
import concept.generated.model.Person;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class HiwiBase extends Person{

	private double gehalt;

	public double getGehalt(){
		return gehalt;
	}

	public void setGehalt(double gehalt){
		this.gehalt = gehalt;
	}

}