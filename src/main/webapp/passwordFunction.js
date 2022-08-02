/**
 * 
 */
 
 function displayPassword(){
			var password = document.getElementById("passwordText");
			if(password.type == "password"){
				password.type = "text";
			}
			else{
				password.type = "password";
			}
		}
function displayPassword2(){
			var password = document.getElementById("reNewPasswordText");
			if(password.type == "password"){
				password.type = "text";
			}
			else{
				password.type = "password";
			}
		}