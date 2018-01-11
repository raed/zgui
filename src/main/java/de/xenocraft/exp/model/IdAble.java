package de.xenocraft.exp.model;

public interface IdAble {

	public default Object getIdObject() {
		return getId();
	}

	public default int getId() {
		return -1;
	}

}
