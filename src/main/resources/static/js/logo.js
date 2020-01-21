if ( navigator.platform.indexOf('Win') != -1 ) {
    window.document.getElementById("wrapper").setAttribute("class", "windows");
} else if ( navigator.platform.indexOf('Mac') != -1 ) {
    window.document.getElementById("wrapper").setAttribute("class", "mac");
}
function validateForm() {
    var x = document.forms["loginBox"]["login"].value;
    var y = document.forms["loginBox"]["password"].value;
    if (x == "" || x == null || y == "" || y == null) {
        alert("All boxes must be filled out");
        return false;
    }
}
