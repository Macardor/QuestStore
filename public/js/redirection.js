function redirection() {
    input = document.getElementsByName("login")[0].value;
    switch (input) {
        case "student":{
            window.location.replace("student/student-homepage.html");
        }
        case "mentor":{
            window.location.replace("student/mentor-homepage.html");
        }
        case "creep":{
            window.location.replace("student/creep-homepage.html");
        }
    }
    console.log(input)
}