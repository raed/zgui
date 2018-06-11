package concept.predefined.dynamic;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Transient;

import concept.predefined.BaseEntity;
import concept.predefined.marker.Invisible;

@Entity
public class Attribute extends BaseEntity implements Comparable<Attribute> {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Invisible
	@Transient
	public String getShortHTMLDisplay() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Attribute other) {
		return Comparator.comparing(Attribute::getName).compare(this, other);
	}

}
