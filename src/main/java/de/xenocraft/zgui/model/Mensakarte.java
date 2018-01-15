package de.xenocraft.zgui.model;

import javax.persistence.AttributeConverter;

public class Mensakarte {

	private int		nr;
	private String	zusatz;

	public Mensakarte() {

	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getZusatz() {
		return zusatz;
	}

	public void setZusatz(String zusatz) {
		this.zusatz = zusatz;
	}

	@Override
	public String toString() {
		return String.format("Mensakarte(%s)", nr + zusatz);
	}

	public static class MensakarteConverter implements AttributeConverter<Mensakarte, String> {

		@Override
		public String convertToDatabaseColumn(Mensakarte mensakarte) {
			if (mensakarte == null) {
				return null;
			}
			return mensakarte.nr + "|" + mensakarte.zusatz;
		}

		@Override
		public Mensakarte convertToEntityAttribute(String dbData) {
			if (dbData == null) {
				return null;
			}
			String[] data = dbData.split("\\|");
			if (data.length != 2) {
				return null;
			}
			Mensakarte karte = new Mensakarte();
			karte.setNr(Integer.parseInt(data[0]));
			karte.setZusatz(data[1]);
			return karte;
		}

	}

}
