// Javascript for registering a new user

function register() {

	var email = $('#email').val();
	var email2 = $('#email2').val();
	var password = $('#password').val();
	var id = makeid();
	
	if(password.length < 6) {
		alert("Password must be longer then 6 characters.");
	} else if (email !== email2){
		alert("Emails do not match.");
	} else if (!validateEmail(email)) {
		alert("Email is not a valid email.");
	} else {

		$.ajax(
				{
					type : "POST",
					url  : "/register_submit/" + id,
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

	} 
}

function validateEmail(email) { 
   	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   	return re.test(email);
} 

function makeid()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 20; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

