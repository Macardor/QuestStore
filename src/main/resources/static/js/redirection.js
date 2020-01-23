function redirection() {
    input = document.getElementsByName("login")[0].value;
    switch (input) {
        case "student":{
            window.location.replace("student/student-homepage.twig");
        }
        case "mentor":{
            window.location.replace("mentor/mentor-homepage.html");
        }
        case "creep":{
            window.location.replace("creep/creep-homepage.html");
        }
    }
    console.log(input)
}