package concept.generated.model.base;

import javax.persistence.*;
import concept.predefined.BaseEntity;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class HiwiBase extends BaseEntity{

	private double gehalt;

	public double getGehalt(){
		return gehalt;
	}

	public void setGehalt(double gehalt){
		this.gehalt = gehalt;
	}

}