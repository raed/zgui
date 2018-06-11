package concept.predefined.web.model.attributes;

import concept.predefined.BaseEntity;
import concept.predefined.web.LinkUtils;
import concept.predefined.web.model.ThymeleafEntityModel;

public class ComplexEntityAttribute extends AEntityAttribute {

	public ComplexEntityAttribute(EntityAttributeFactory factory) {
		super(factory);
	}

	public ThymeleafEntityModel<BaseEntity> getEntityModel(BaseEntity entity) {
		BaseEntity targetObject = getValueObject(entity);
		if (targetObject == null) {
			try {
				targetObject = (BaseEntity) getType().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		ThymeleafEntityModel<BaseEntity> entityModel = new ThymeleafEntityModel<BaseEntity>(targetObject, getService());
		entityModel.setParent(getDisplayName());
		return entityModel;
	}

	@Override
	public String getHTMLValue(BaseEntity object) {
		BaseEntity gotObject = getValueObject(object);

		if (gotObject == null) {
			return NA_DISPLAY;
		}

		return LinkUtils.createLinkFor(gotObject).createHTMLLink();
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getHTMLInput(BaseEntity object) {
		// TODO move HTML to view
		String selectFormat = "<select class=\"complexEntitySelect\" name=\"%1$s\" id=\"%1$s\">%2$s</select>";
		String optionFormat = "<option value=\"%1$s\" %3$s>%2$s</option>";
		String options = "<option value=\"0\">Neu</option>";

		for (BaseEntity e : getService().getAll((Class<BaseEntity>) getType())) {
			String selected = e.equals(getValueObject(object)) ? "selected" : "";
			options += String.format(optionFormat, e.getId(), e.getShortHTMLDisplay(), selected);
		}
		return String.format(selectFormat, getAttributePath(), options);
	}

	@Override
	public BaseEntity getValueObject(BaseEntity object) {
		return (BaseEntity) super.getValueObject(object);
	}

}