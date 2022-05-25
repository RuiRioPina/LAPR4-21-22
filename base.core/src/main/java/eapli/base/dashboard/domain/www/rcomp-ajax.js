
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.
function refreshAGVsDashboardInfo() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("agvsDashboard");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.open("GET", "/agvsDashboard", true);
    request.timeout = 2000;
    request.send();
}

function refreshPersonalInfo() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("personalInfo");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.open("GET", "/personalInfo", true);
    request.timeout = 2000;
    request.send();
}



