
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
		var table = "<tr><td></td><td>Name     </td><td>Value(%)</td><td>Your Percent Score</td></tr>";
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
							table += '<tr><td></td><td><p class="type">' + result.name + '</p></td><td><p class="maxVal">' + result.value + '</p></td><td><input id="score' + i + '" onKeyPress="return numbersonly(this, event)" placeholder="Your score" value=""/></td></tr>';
						},async:false,
						error: function (jqXHR, exception) {
							alert("Failed to get class attributes. Error 2.");
						}
				});
		}
		
		table += '<tr><td></td><td>Percent Wanted</td><td></td><td><input id="gradewanted" placeholder="Percent " value=""/></td></tr>';

		newTable.innerHTML = table;
		document.getElementById('dynamicInput').appendChild(newTable);
	}

}

function calculateGrade()
{
        var scoreArray = [];
        var attributesNames = [];
        var attributesValues = [];
        for(var i = 0; i < totalAtt; i++)
        {           
            attributesNames[i] = $('.type')[i].innerHTML;
            attributesValues[i] = $('.maxVal')[i].innerHTML;
            scoreArray[i] = parseInt($('#score' + i).val()) || 0;
        }
        
   $.ajax(
				{
					type : "POST",
					url  : "/calculate",
					data : {
						"total" : attributesValues,
						"attNames" : attributesNames,
                                                "attValues" : scoreArray,
						"goal" :  $('#gradewanted').val()
					},
					success : function(result) {
                                            if(result == 0)
                                                document.getElementById('result_score').innerHTML = "You already obtained goal percent";
                                            else if(result == -1)
						document.getElementById('result_score').innerHTML = "Immpossible to achieve goal percent";
                                            else
                                                document.getElementById('result_score').innerHTML = result + "%";
					},async:false,
					error: function (jqXHR, exception) {
						alert("Failed to add the class.");
					},
					traditional: true
				});
         
	                        
         
}