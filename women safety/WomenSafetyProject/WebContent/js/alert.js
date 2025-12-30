function sendAlert() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(pos => {
            let loc = pos.coords.latitude + "," + pos.coords.longitude;
            fetch("AlertServlet", {
                method: "POST",
                headers: {"Content-Type":"application/x-www-form-urlencoded"},
                body: "location=" + loc
            }).then(() => alert("Emergency Alert Sent!"));
        });
    }
}
