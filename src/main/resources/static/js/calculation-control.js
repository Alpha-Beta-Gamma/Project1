
var classId = ""; //use this to find out what data to load for this page

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
	alert("HI2");
	
	classId = location.search.split('id=')[1]; //gets what classid we need
	
	if (classId == "undefined"){
		alert("There was a problem, no classId was set!")
	} else {
		var newTable = document.createElement("TABLE");
		var table = "<tr><td></td><td>Name     </td><td>Value    </td><td>Your Score</td></tr>";
		var attCount = 0;
		
		$.ajax(
				{
					type : "GET",
					url  : "/attcount/" + classId,
					data : {
					},
					success : function(result) {
						attCount = result;
					},async:false,
					error: function (jqXHR, exception) {
						alert("Failed to get class attributes.");
					}
				});
		
		for (var i = 0; i < attCount; i++){
			alert("HI1");
			$.ajax(
					{
						type : "GET",
						url  : "/classAtt",
						data : {
							"classId" : classId,
							"index" : i
						},
						success : function(result) {
							table += '<tr><td></td><td>' + result.name + '</td><td>' + result.value + '</td><td><input id="score' + i + '" onKeyPress="return numbersonly(this, event)" value=""/></td></tr>';
						},async:false,
						error: function (jqXHR, exception) {
							alert("Failed to get class attributes. Error 2.");
						}
				});
		}

		newTable.innerHTML = table;
		document.getElementById('dynamicInput').appendChild(newTable);
	}

}

function calculateGrade()
{
	alert(classId);
	//TODO will have to use loop to get each id="score + INDEX"
	//can use example from loading text into combo boxs
}