
function searchForClass() {
	
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
}

function goToClass(classid) {
	
	//USE CLASSID TO DISPLAY CLASS INFO
	window.location.href = '/classlookup.html';
}

function goToCreateClass(){
	window.location.href = '/editclass.html';
}

function addAllSchools(){

	$.ajax(
			{
				type : "GET",
				url  : "/schools",
				data : {
				},
				success : function(result) {
					
					for (var s in result){
					    var combo = document.getElementById("schoolCombo");
					     
					    var option = document.createElement("option");
					    option.text = s.name; //TODO line is wrong-----------------------------------------------
					    option.value = result.id; //TODO line is wrong----------------------------
					    try {
					        combo.add(option, null); //Standard
					    }catch(error) {
					        combo.add(option); // IE only
					    }						
					}
					

				},
				error: function (jqXHR, exception) {
					alert("Failed to get schools for combobox.");
				}
			});

		
		alert("DONE! :] ...Debug");

}


function addCombo() {
    var textb = document.getElementById("schooltxt");
    var combo = document.getElementById("schoolCombo");
     
    var option = document.createElement("option");
    option.text = textb.value;
    option.value = textb.value;
    try {
        combo.add(option, null); //Standard
    }catch(error) {
        combo.add(option); // IE only
    }
    textb.value = "";
}