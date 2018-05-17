$(function() {

	const ENTITYVIEW_PATH = "/entity/";

	$(".tabs").tabs();

	$(".complexEntitySelect").change(
			function() {
				var select = $(this);
				var type = select.attr("name");
				var selected = select.val();

				$.get(ENTITYVIEW_PATH + type + "/" + selected + "/table?edit=true",
						function(data) {
							var targetDiv = $("#tab_" + type);
							targetDiv.html(data);
						});

			});

});
