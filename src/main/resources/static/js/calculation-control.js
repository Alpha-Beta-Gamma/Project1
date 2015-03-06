
var classId = ""; //use this to find out what data to load for this page
var totalAtt = 0;

function goCalcPage(id) {
	window.location.href = "/calculationpage.html?id=" + id;
}

function goToClassLookUp(){
	window.location.href = "/classlookup.html";
}

function goToCreateClass(id) {
	window.location.href = "/editclass.html?id=" + id;
}

// copyright 1999 Idocs, Inc. http://www.idocs.com
// Distribute this script freely but keep this notice in place
function numbersonly(myfield, e, dec)
{
	var key;
	var keychar;
	
	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	
	// control keys
	if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
	   return true;
	
	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	
	// decimal point jump
	else if (dec && (keychar == "."))
	   {
	   myfield.form.elements[dec].focus();
	   return false;
	   }
	else
	   return false;
}

//adds all data to table
function addData(){
	
	classId = location.search.split('id=')[1]; //gets what classid we need
	
	if (classId == "undefined"){
		alert("There was a problem, no classId was set!")
	} else {
		var newTable = document.createElement("TABLE");
		var table = "<tr><td></td><td>Name     </td><td>Value    </td><td>Your Score</td></tr>";
		totalAtt = 0;
		
		$.ajax(
				{
					type : "GET",
					url  : "/attcount/" + classId,
					data : {
					},
					success : function(result) {
						totalAtt = result;
					},async:false,
					error: function (jqXHR, exception) {
						alert("Failed to get class attributes.");
					}
				});
		
		for (var i = 0; i < totalAtt; i++){
			$.ajax(
					{
						type : "GET",
						url  : "/classAtt",
						data : {
							"classId" : classId,
							"index" : i
						},
						success : function(result) {
							table += '<tr><td></td><td>' + result.name + '</td><td>' + result.value + '</td><td><input id="score' + i + '" onKeyPress="return numbersonly(this, event)" placeholder="Your score" value=""/></td></tr>';
						},async:false,
						error: function (jqXHR, exception) {
							alert("Failed to get class attributes. Error 2.");
						}
				});
		}
		
		table += '<tr><td></td><td>Letter Grade Wanted</td><td></td><td><input id="gradewanted" placeholder="Letter grade you want" value=""/></td></tr>';

		newTable.innerHTML = table;
		document.getElementById('dynamicInput').appendChild(newTable);
	}

}

function calculateGrade()
{
	//goes through all input boxes and gets the total
	var totalGot = 0;
	
	for (var i = 0; i < totalAtt; i++){
		totalGot += parseInt($('#score' + i).val()) || 0;
	}
	
	var wantedGrade = $('#gradewanted').val();
	var output = "";
	var missing = 0;
	
	if (wantedGrade == "A+"){
		var min = 97;
		missing = min - totalGot; 
	}else if (wantedGrade == "A"){
		var min = 93;
		missing = min - totalGot;
	}else if (wantedGrade == "A-"){
		var min = 90;
		missing = min - totalGot;
	}else if (wantedGrade == "B+"){
		var min = 87;
		missing = min - totalGot;
	}else if (wantedGrade == "B"){
		var min = 83;
		missing = min - totalGot;
	}else if (wantedGrade == "B-"){
		var min = 80;
		missing = min - totalGot;
	}else if (wantedGrade == "C+"){
		var min = 77;
		missing = min - totalGot;
	}else if (wantedGrade == "C"){
		var min = 73;
		missing = min - totalGot;
	}else if (wantedGrade == "C-"){
		var min = 70;
		missing = min - totalGot;
	}else if (wantedGrade == "D+"){
		var min = 67;
		missing = min - totalGot;
	}else if (wantedGrade == "D"){
		var min = 63;
		missing = min - totalGot;
	}else if (wantedGrade == "D-"){
		var min = 60;
		missing = min - totalGot;
	}else if (wantedGrade == "F"){
		var min = 0;
		missing = min - totalGot;
	}else {
		output = "Unkown letter grade.";
	}
	
	if (output == ""){
		output = "You need " + missing + " more points to get the grade you desire.";
	}

	document.getElementById('result_score').innerHTML = output;
}