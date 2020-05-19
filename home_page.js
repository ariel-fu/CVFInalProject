function change(){
    var elem = document.getElementById("change");
	
 var stringInDoc = document.getElementById("change").innerHTML;
if (stringInDoc==="CHANGE"){
document.getElementById("change").innerHTML = 'Doggos are the best!';
 } else if(stringInDoc===("Doggos are the best!")){
 	 document.getElementById("change").innerHTML = 'Puppies are adorable!';

 } else {
 	  document.getElementById("change").innerHTML = 'CHANGE';
}
}



