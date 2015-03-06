
var classId = ""; //use this to find out what data to load for this page

function goCalcPage(id) {
	window.location.href = "/calculationpage.html?id=" + id;
}

function goToClassLookUp(){
	window.location.href = "/classlookup.html";
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
		
		//TODO run for loop for each attribute in classes attibute list and add row to above table 
		//for (int i = 0; i < ????; i++)
		//table += "<tr><td></td><td>THENAME</td><td>THE VALUE</td><td><input id="score" + i + "" onKeyPress="return numbersonly(this, event)" value=""/></td></tr>";
		
		newTable.innerHTML = table;
		
		
		document.getElementById('dynamicInput').appendChild(newTable);
	}
}

function calculateGrade()
{
	alert(classId);
	//TODO will have to use loop to get each id="score + NUMBER"
	//can use example from loading text into combo boxs
}