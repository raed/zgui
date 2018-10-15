package concept.predefined;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;

import concept.predefined.dynamic.Attribute;
import concept.predefined.marker.ExtraAttributes;

@MappedSuperclass
public abstract class ExtendedEntity extends BaseEntity {

	private Map<Attribute, String> extraAttributes = new HashMap<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@ExtraAttributes
	public Map<Attribute, String> getExtraAttributes() {
		return extraAttributes;
	}

	public void setExtraAttributes(Map<Attribute, String> extraAttributes) {
		this.extraAttributes = extraAttributes;
	}

}
