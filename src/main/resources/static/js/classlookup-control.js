
function searchForClass() {

	alert("DEBUG - This is a good sign");
	
	$.ajax(
			{
				type : "GET",
				url  : "/search",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}
