
function searchForClass() {
	
	var schoolId = $('#schoolCombo').val();
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
	//gets all the schools in database and adds them to the combobox for user selection upon search
	
	var schoolCount = 0;
	
	$.ajax(
			{
				type : "GET",
				url  : "/schoolcount",
				data : {
				},
				success : function(result) {
					schoolCount = result;
				},
				error: function (jqXHR, exception) {
					alert("Failed to get schools.");
				}
			});
	
	for (var i = 0; i < schoolCount; i++){
	$.ajax(
			{
				type : "GET",
				url  : "/schools",
				data : {
					"schoolId" : i
				},
				success : function(result) {

					    var combo = document.getElementById("schoolCombo");
					     
					    var option = document.createElement("option");
					    option.text = result.name; 
					    option.value = result.id;
					    try {
					        combo.add(option, null); //Standard
					    }catch(error) {
					        combo.add(option); // IE only
					    }						
				},
				error: function (jqXHR, exception) {
					alert("Failed to get schools for combobox.");
				}
		});
	}
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