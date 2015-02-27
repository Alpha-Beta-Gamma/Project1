
function saveClass() {
	
	var schoolId = parseInt($('#schoolCombo').val());
	var schoolText = $('#schoolText').val();
	var uniqueText = $('#uniqueText').val();
	var nameText = $('#nameText').val();
	var subjectText = $('#subjectText').val();
	var numberText = $('#numberText').val();
	var instructorText = $('#instructorText').val();

	if ((schoolId === -1 && schoolText && uniqueText && nameText && subjectText && numberText && instructorText)
			|| (uniqueText && nameText && subjectText && numberText && instructorText)){
		
		if (schoolId === -1){
			$.ajax(
					{
						type : "POST",
						url  : "/add_school",
						data : {
							"name" : schoolText
						},
						success : function(result) {
							window.location.href = '/classlookup.html';
						},
						error: function (jqXHR, exception) {
							alert("Failed to add the school. Please check the inputs.");
						}
					});
			
			/*
			 //CODE BELOW DOESNT WORK for some reason, so you cant add a class and school at the same time
			alert("BUG, code will not work without this alert");
			
			$.ajax(
				{
					type : "POST",
					url  : "/add_class",
					data : {
						"name" : nameText,
						"id" : '_' + uniqueText,
						"uniqueNumber" : uniqueText,
						"subject" : subjectText,
						"instructor" : instructorText,
						"school" : schoolText
					},
					success : function(result) {
						window.location.href = '/classlookup.html';
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the class. Please check the inputs.");
					}
				});
				*/
		} else{
			$.ajax(
					{
						type : "POST",
						url  : "/add_class_no_new_school",
						data : {
							"name" : nameText,
							"id" : schoolId + '_' + uniqueText,
							"uniqueNumber" : uniqueText,
							"subject" : subjectText,
							"instructor" : instructorText,
							"school" : schoolText
						},
						success : function(result) {
							window.location.href = '/classlookup.html';
						},
						error: function (jqXHR, exception) {
							alert("Failed to add the class. Please check the inputs.");
						}
					});
		}
		
	} else {
		alert("You missed a field, sorry.");
	}
	
}

function goToClass(classid) {
	
	//USE CLASSID TO DISPLAY CLASS INFO
	window.location.href = '/classlookup.html';
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