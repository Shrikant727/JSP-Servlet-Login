	function showAlert(message){
		if(message!="null")
		alert(message);
	}
	function register() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
	let form=document.getElementById('regForm');
    // Email validation
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Invalid email. Please enter a valid email address.");
        return;
    }

    // Password length validation
    if (password.length <= 6) {
        alert("Invalid password. Password must be greater than 6 characters.");
        return;
    }
    form.submit();
}

function login(){
	let password = document.getElementById("password").value;
	 if (password.length <= 6) {
        alert("Invalid password. Password must be greater than 6 characters.");
        return;
    }
    let form=document.getElementById('loginForm');
    form.submit();
}
