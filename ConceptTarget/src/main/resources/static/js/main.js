$(function() {

	const ENTITYVIEW_PATH = "/entity/";

	$(".tabs").tabs();

	$(".complexEntitySelect").change(function() {
		var select = $(this);
		var type = select.attr("name");
		var selected = select.val();
		var targetDiv = $("#tab_" + type);

		$.get(ENTITYVIEW_PATH + type + "/" + selected + "/table?edit=true", function(data) {
			targetDiv.html(data);
		});
	});

	$(".colAttrRemoveButton").click(function() {
		$(this).closest("li").remove();
	});

	$(".colAttrAddButton").click(function() {
		var attribute = $(this).data("attributepath");
		var list = $(this).parent().find("ul");
		list.append("<li><input type=\"text\" name=\"" + attribute + "\" > <button class=\"xButton colAttrRemoveButton\" type=\"button\">X</button></li>");

		$(".colAttrRemoveButton").click(function() {
			$(this).closest("li").remove();
		});
	});

	$(".disableExtraAttribute").click(function() {
		$(this).parent().parent().find(".extraAttributeInput").prop("disabled", function(i, v) {
			return !v;
		});
	});
});
