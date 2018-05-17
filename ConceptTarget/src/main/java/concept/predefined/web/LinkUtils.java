package concept.predefined.web;

import concept.predefined.BaseEntity;

public class LinkUtils {

	public static <T extends BaseEntity> Link createLinkFor(T entity) {
		String display = getLinkDisplay(entity);
		String target = TestController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId();
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createIDLinkFor(T entity) {
		String display = getLinkDisplay(entity);
		String target = TestController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId();
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createEditLinkFor(T entity) {
		String display = getLinkDisplay(entity);
		String target = TestController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId() + "/edit";
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createSaveLinkFor(T entity) {
		String display = getLinkDisplay(entity);
		String target = TestController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId() + "/save";
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createDeleteLinkFor(T entity) {
		String display = getLinkDisplay(entity);
		String target = TestController.ENTITYVIEW_PATH + entity.getClass().getSimpleName().toLowerCase() + "/" + entity.getId() + "/delete";
		return new Link(display, target);
	}

	public static <T extends BaseEntity> Link createAddLinkFor(Class<T> targetClass) {
		String display = targetClass.getSimpleName() + " hinzufügen";
		String target = TestController.ENTITYVIEW_PATH + targetClass.getSimpleName().toLowerCase() + "/new";
		return new Link(display, target);
	}

	private static <T extends BaseEntity> String getLinkDisplay(T entity) {
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
