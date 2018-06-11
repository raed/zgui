package concept.predefined;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import concept.predefined.marker.Invisible;

@MappedSuperclass
public abstract class BaseEntity {

	private int id;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Transient
	public boolean isNew() {
		return id == 0;
	}

	@Invisible
	@Transient
	public String getShortHTMLDisplay() {
		return getClass().getSimpleName() + " ID: " + id;
	}

}
