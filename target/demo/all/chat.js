let username = decodeURIComponent(window.location.search.split('=')[1]);
const worker = new Worker('worker.js');

worker.onmessage = function(event) {
    if (event.data.type === 'message') {
        document.getElementById("messages").innerHTML += event.data.data + "\n";
    } else if (event.data.type === 'error') {
        alert("Error in SSE: " + event.data.data);
    }
};

function sendMessage() {
    let message = document.getElementById("messageInput").value;
    let username = decodeURIComponent(window.location.search.split('=')[1]);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/myApp/sse", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    console.log("msg=" + encodeURIComponent(message) + "&username=" + encodeURIComponent(username));
    xhr.send("msg=" + encodeURIComponent(message) + "&username=" + encodeURIComponent(username));
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.readyState + " should be sent  " + xhr.status);
            document.getElementById("messageInput").value = "";
        }
    };
}

function receiveMessages() {
    if (window.Worker) {
        worker.postMessage({ type: 'start', url: '/myApp/sse' });
    } else {
        alert("Web Workers are not supported");
    }
}
