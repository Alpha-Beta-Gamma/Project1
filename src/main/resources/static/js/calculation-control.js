
var classId = ""; //use this to find out what data to load for this page

function goCalcPage(id) {
	classId = id;
	window.location.href = "/calculationpage.html";
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

function calculateGrade()
{
	alert(classId);
    
}
function selectGradeInput()
{
    
}
function createGradeInput()
{
	alert(classId);
    window.location.href = '/editclass.html';
}
function getSchoolAndClass()
{
//var name = window.location.search;
//document.write(name);
//$('#school').text(name);
}