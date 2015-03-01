
var classId = "";
var name = "";

function searchForClass() {
	
	var schoolId = parseInt($('#schoolCombo').val());
	var searchText = $('#searchText').val();
	classId = schoolId + "_" + searchText;
	
	if (searchText) {
		$.ajax(
				{
					type : "GET",
					url  : "/classes/" + classId,
					data : {
					},
					success : function(result) {
						if (result.name){
							$('#result_name').text(result.name);
							$('#result_subject').text(result.subject);
							$('#result_uniqueNumber').text(result.uniqueNumber);
							$('#result_instructor').text(result.instructor);
							$('#result_link').html("<button onclick=\"goToClass()\" >Go!</button>");
						} else {
							$('#result_name').text("Software");    
                                                        name = "Software";
							$('#result_subject').text("CS");
							$('#result_uniqueNumber').text("12345");
							$('#result_instructor').text("yusun");							
							$('#result_link').html("<button onclick=\"goToClass()\" >Go!</button>");
						}
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the class.");
					}
				});
	} else {
		alert("Select a school and type a criteria.");
	}
}

function goToClass() {

	//TODO USE classId TO DISPLAY CLASS INFO
	window.location.href = "/calculationpage.html";
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
	
	alert("BUG to fix, combobox values will not load without this alert");
	
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