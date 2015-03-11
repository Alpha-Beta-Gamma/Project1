function attemptlogin() {

	var email = $('#email').val();
	var password = $('#password').val();
	
	if(password.length < 6) {
		alert("Password must be longer then 6 characters.");
	} else if (!validateEmail(email)) {
		alert("Email is not a valid email.");
	} else {
	window.location.href = '/classlookup.html';
		//alert("Function not available yet");
		
        /*

		$.ajax(
				{
					type : "POST",
					url  : "/log_in/" + email,
					data : {
						"email" : email,
						"password" : password
					},
					success : function(result) {
						window.location.href = '/classlookup.html';
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
         */

	} 
}
	

function gotoregister() {
   	window.location.href = 'register.html';
}

function gotoclasslookup() {
   	window.location.href = 'classlookup.html';
}

function validateEmail(email) { 
   	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   	return re.test(email);
}