var req = null;
    function submitForm(){
        req = new XMLHttpRequest();
        req.open( "GET", "file.txt?t= " + new Date().getTime(), true);

        req.onreadystatechange = handleReq;
        req.send(null);
    }
    function handleReq() {
        if (req.readyState == 4) {
            document.getElementById("response").innerHTML = req.responseText;
        }
    }

    function validateName() {
        name = document.getElementById("userName").value;
        xhr = new XMLHttpRequest();
        xhr.open("GET", "jsp/validateName.jsp?name=" + name, true);
        xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
        var text = xhr.responseText;
        document.getElementById("nameStatus").innerHTML = text;
    }
    };
        xhr.send(null);
    }

    function updateSchedule() {
        xhr = new XMLHttpRequest();
        xhr.open("GET", "jsp/table.jsp?t="+ new Date().getTime(), true);
        xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
        document.getElementById("schedule").innerHTML = xhr.responseText;
    }
    };
        xhr.send(null);

    }
