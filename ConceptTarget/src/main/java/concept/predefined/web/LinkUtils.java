package concept.predefined.web;

import concept.predefined.BaseEntity;

public class LinkUtils {

	public static Link createLinkFor(BaseEntity entity) {
		String display = getLinkDisplay(entity);
		String target = getEntityViewPathFor(entity);
		return new Link(display, target);
	}

	public static Link createEditLinkFor(BaseEntity entity) {
		String display = getLinkDisplay(entity);
		String target = getEntityViewPathFor(entity) + "/edit";
		return new Link(display, target);
	}

	public static Link createSaveLinkFor(BaseEntity entity) {
		String display = getLinkDisplay(entity);
		String target = getEntityViewPathFor(entity) + "/save";
		return new Link(display, target);
	}

	public static Link createDeleteLinkFor(BaseEntity entity) {
		String display = getLinkDisplay(entity);
		String target = getEntityViewPathFor(entity) + "/delete";
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createAddLinkFor(Class<T> targetClass) {
		String display = targetClass.getSimpleName() + " hinzufügen";
		String target = UniversalController.ENTITYVIEW_PATH + targetClass.getSimpleName().toLowerCase() + "/new";
		return new Link(display, target);
	}

	private static String getEntityViewPathFor(BaseEntity entity) {
		return UniversalController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId();
	}

	private static String getLinkDisplay(BaseEntity entity) {
		return entity.getShortHTMLDisplay();
	}

	public static class Link implements Comparable<Link> {

		private String	display;
		private String	target;

		public Link(String display, String target) {
			this.display = display;
			this.target = target.toLowerCase();
		}

		public String getDisplay() {
			return display;
		}

		public void setDisplay(String display) {
			this.display = display;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		@Override
		public int compareTo(Link o) {
			return this.getDisplay().compareTo(o.getDisplay());
		}

		public String createHTMLLink() {
			return "<a href=\"" + target + "\">" + display + "</a>";
		}
	}
}
