$(document).ready(function() {
	$(".jssuchfeld").on("input", function() {
		var suchtext = this.value.toLowerCase();

		var tablerows = $(this).parent().parent().find(".entitytable > tbody > tr");

		tablerows.each(function(i, row) {
			$(row).hide();

			$(row).children().each(function(j, td) {
				if ($(td).text().toLowerCase().includes(suchtext)) {
					$(row).show();
				}
			});
		});

	});
});