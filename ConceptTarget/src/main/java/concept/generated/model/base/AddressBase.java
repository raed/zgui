package concept.generated.model.base;

import javax.persistence.*;
import concept.predefined.BaseEntity;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class AddressBase extends BaseEntity{

	private String strasse;
	private int hausnummer;
	private String stadt;
	private String land;

	public String getStrasse(){
		return strasse;
	}

	public void setStrasse(String strasse){
		this.strasse = strasse;
	}

	public int getHausnummer(){
		return hausnummer;
	}

	public void setHausnummer(int hausnummer){
		this.hausnummer = hausnummer;
	}

	public String getStadt(){
		return stadt;
	}

	public void setStadt(String stadt){
		this.stadt = stadt;
	}

	public String getLand(){
		return land;
	}

	public void setLand(String land){
		this.land = land;
	}

}