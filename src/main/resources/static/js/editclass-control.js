
function saveClass() {
	
	//TODO! 
	
	/*
	var schoolId = $('#searchSchool').val();
	var searchText = $('#searchText').val();
	var classId = schoolId + "_" + searchText;
	
	if (schoolId && searchText) {
		$.ajax(
				{
					type : "GET",
					url  : "/classes/" + classId,
					data : {
					},
					success : function(result) {
						$('#result_name').text(result.name);
						$('#result_subject').text(result.subject);
						$('#result_uniqueNumber').text(result.uniqueNumber);
						$('#result_instructor').text(result.instructor);
						$('#result_link').html("<a class=\"button\"  href=\"classlookup.html\" >Go!</a>");
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the class.");
					}
				});
	} else {
		alert("Select a school and type a criteria.");
	}
	*/
}

function goToClass(classid) {
	
	//USE CLASSID TO DISPLAY CLASS INFO
	window.location.href = '/classlookup.html';
}
