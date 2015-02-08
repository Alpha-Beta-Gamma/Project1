// Javascript for registering a new user


function register() {

	var email1 = $('#email1').val();
	var email2 = $('#email2').val();
	var password = $('#password').val();

	if (email1) { //&& (email1 === email2)
	alert("DEBUG");
		$.ajax(
				{
					type : "POST",
					url  : "/register_submit/" + email1,
					data : {
						"email" : email1,
						"pass" : pass
					},
					success : function(result) {
						//location.reload();
						window.location.href = '/classlookup.html';
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});

	} else {
		alert("Invalid email");
	}
}
