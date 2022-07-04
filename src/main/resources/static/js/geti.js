function loadGetMsg(name){
    localStorage.setItem("user",name)
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsg").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/index");
    xhttp.send()
}
