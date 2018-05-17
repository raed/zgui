package concept.generated.model;

import javax.persistence.*;
import concept.generated.model.base.AddressBase;
import concept.predefined.web.Invisible;

@Entity
public class Address extends AddressBase {

	@Override
	@Transient
	@Invisible
	public String getShortHTMLDisplay() {
		return getStrasse() + " " + getHausnummer() + " " + getStadt();
	}

}