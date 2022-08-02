/**
 * 
 */
 function isDelete(){
	var remove = confirm("Do you want to delete this note?");
	if(remove == false){
		event.preventDefault();
	}
}

 function isRemove(){
	var remove = confirm("Do you want to remove this user from sharing?");
	if(remove == false){
		event.preventDefault();
	}
}