document.addEventListener("DOMContentLoaded", function () {
    var loginToken = sessionStorage.getItem("loginToken");

    document.getElementsByClassName("loginToken").value = loginToken;
});