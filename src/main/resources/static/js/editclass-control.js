
var counterOfAttributes = 0;
var limitOfAttributes = 20;
var classId = ""; //use this to find out what data to load for this page

function addInput(divName){
	
     if (counterOfAttributes >= limitOfAttributes)  {
          alert("You have reached the limit of adding class inputs");
     }
     else {
    	 counterOfAttributes++;
         var newdiv = document.createElement('div');
         var newdiv2 = document.createElement('div');
         newdiv.innerHTML = "<input type='text' id='attName" + counterOfAttributes + "' placeholder='Name of Test/Assignment " + counterOfAttributes +  "' name='nameInputs'>";
         document.getElementById(divName).appendChild(newdiv);
         newdiv2.innerHTML = "<input type='text' id='attValue" + counterOfAttributes + "'placeholder='Value of Test/Assignment " + counterOfAttributes +  "' name='valueInputs'>";
         document.getElementById(divName).appendChild(newdiv2);
     }
}

function saveClass() {
	
	var schoolId = parseInt($('#schoolCombo').val());
	var schoolText = $('#schoolText').val();
	var uniqueText = $('#uniqueText').val();
	var nameText = $('#nameText').val();
	var subjectText = $('#subjectText').val();
	var numberText = $('#numberText').val();
	var total = $('#classValueText').val();
	var instructorText = $('#instructorText').val();
	var classTotalValue = $('#classValueText').val();
	
	var attributesNames = [];
	var attributesValues = [];
	
	for	(var i = 0; i < counterOfAttributes; i++) {
		var iplus = i+1;
		attributesNames[attributesNames.length] = $('#attName' + iplus).val(); 
		attributesValues[attributesValues.length] = $('#attValue' + iplus).val(); 
	}

	//TODO add check to make sure all values for names/values are input by user
	
	if ((schoolId === -1 && schoolText && uniqueText && nameText && subjectText && numberText && instructorText && classValueText && total)
			|| (uniqueText && nameText && subjectText && numberText && instructorText && classValueText && total)){
		
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
						},async:false,
						error: function (jqXHR, exception) {
							alert("Failed to add the school. Please check the inputs.");
						}
					});

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
						"school" : schoolText,
						"total" : total,
						"attNames" : attributesNames,
						"attValues" : attributesValues
					},
					success : function(result) {
						window.location.href = '/classlookup.html';
					},async:false,
					error: function (jqXHR, exception) {
						alert("Failed to add the class. Please check the inputs. You must have atleast one Test/Assignment.");
					},
					traditional: true
				});
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
							"school" : schoolText,
							"total" : total,
							"attNames" : attributesNames,
							"attValues" : attributesValues
						},
						success : function(result) {
							window.location.href = '/classlookup.html';
						},
						error: function (jqXHR, exception) {
							alert("Failed to add the class. Please check the inputs. You must have atleast one Test/Assignment.");
						},
						traditional: true
					});
		}
		
	} else {
		alert("You missed a field, please recheck.");
	}
}

function goToCreateClass(id) {
	window.location.href = "/editclass.html?id=" + id;
}

function addAllSchools(){
	//gets all the schools in database and adds them to the combobox for user selection
	
	classId = location.search.split('id=')[1]; //Gets what classid we need
	
	var schoolCount = 0;
	
	$.ajax(
			{
				type : "GET",
				url  : "/schoolcount",
				data : {
				},
				success : function(result) {
					schoolCount = result;
				},async:false,
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
					},async:false,
					error: function (jqXHR, exception) {
						alert("Failed to get schools for combobox.");
					}
			});
	}
	
	fillInFields();
}

function fillInFields(){
	classId = location.search.split('id=')[1];
	
	if (classId == "undefined"){
		
	} else {
	
		var array = classId.split("_");
		var schoolId = array[0];
		var unique = array[1];
	
		$.ajax(
				{
					type : "GET",
					url  : "/classes/" + classId,
					data : {
					},
					success : function(result) {
						document.getElementById("schoolCombo").value = schoolId; //by id or text?
						document.getElementById("uniqueText").value = unique;
						document.getElementById("nameText").value = result.name;
						document.getElementById("subjectText").value = result.subject;
						document.getElementById("instructorText").value = result.instructor;	
						document.getElementById("classValueText").value = result.total;						
					},
					error: function (jqXHR, exception) {
						alert("Failed to get class.");
					}
				});
		
		//TODO add way to add assignment/tests dynamically
		
	}
}


